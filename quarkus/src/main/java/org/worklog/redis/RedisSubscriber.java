package org.worklog.redis;

import java.util.function.Consumer;

import org.worklog.accesslog.AccessLogService;
import org.worklog.filebeat.FilebeatData;

import io.quarkus.redis.datasource.RedisDataSource;
import io.quarkus.redis.datasource.pubsub.PubSubCommands;
import io.quarkus.runtime.Startup;
import io.smallrye.common.annotation.Blocking;
import io.smallrye.mutiny.Uni;
import io.smallrye.mutiny.infrastructure.Infrastructure;
import jakarta.annotation.PreDestroy;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
@Startup
@Blocking
public class RedisSubscriber implements Consumer<FilebeatData> {
    @Inject
    private AccessLogService accessLogService;
    private final PubSubCommands<FilebeatData> pub;
    private final PubSubCommands.RedisSubscriber subscriber;

    public RedisSubscriber(RedisDataSource ds) {
        pub = ds.pubsub(FilebeatData.class);
        subscriber = pub.subscribe("logs-channel", this);
    }

    @Override
    public void accept(FilebeatData notification) {
        Uni.createFrom().item(notification)
                .runSubscriptionOn(Infrastructure.getDefaultWorkerPool())
                .subscribe().with(notif -> {
                    System.out.println(notif.getJsonFromCsv());
                    // TODO: send to elasticsearch
                }, failure -> {
                    failure.printStackTrace();
                });
    }

    @PreDestroy
    public void terminate() {
        subscriber.unsubscribe();
    }
}
