package org.worklog.accesslog;

import org.worklog.elasticsearch.ElascticsearchSession;
import org.worklog.elasticsearch.exception.CreateSessionSearchException;

public class AccessLogService extends ElascticsearchSession {

    public AccessLog save(AccessLog accessLog) throws CreateSessionSearchException {
        return withSession(searchSession -> {
            searchSession.indexingPlan().add(accessLog);
            return accessLog;
        });
    }
}
