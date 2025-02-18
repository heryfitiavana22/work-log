package org.worklog.redis;

import java.util.function.Consumer;

import org.worklog.filebeat.FilebeatData;

import io.quarkus.redis.datasource.RedisDataSource;
import io.quarkus.redis.datasource.pubsub.PubSubCommands;
import io.smallrye.mutiny.Uni;
import io.smallrye.mutiny.infrastructure.Infrastructure;
import jakarta.annotation.PreDestroy;

public class RedisSubscriber implements Consumer<FilebeatData> {
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
