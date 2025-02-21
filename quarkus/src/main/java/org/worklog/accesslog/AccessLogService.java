package org.worklog.accesslog;

import org.worklog.elasticsearch.CreateSessionSearchException;
import org.worklog.elasticsearch.ElascticsearchSession;

public class AccessLogService extends ElascticsearchSession {

    public AccessLog save(AccessLog accessLog) throws CreateSessionSearchException {
        return withSession(searchSession -> {
            searchSession.indexingPlan().add(accessLog);
            return accessLog;
        });
    }
}
