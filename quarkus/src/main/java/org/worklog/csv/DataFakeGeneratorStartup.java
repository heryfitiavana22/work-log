package org.worklog.csv;

import org.worklog.accesslog.AccessLogGenerator;

import io.quarkus.runtime.Startup;
import jakarta.enterprise.context.ApplicationScoped;

@Startup
@ApplicationScoped
public class DataFakeGeneratorStartup {
    private AccessLogGenerator accessLogGenerator;

    public DataFakeGeneratorStartup(AccessLogGenerator accessLogGenerator) {
        this.accessLogGenerator = accessLogGenerator;
        accessLogGenerator.startGeneratingPast(100);
        accessLogGenerator.startGeneratingNow(1, 10);
    }

    public AccessLogGenerator getAccessLogGenerator() {
        return accessLogGenerator;
    }
}
