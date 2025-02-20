package org.worklog.accesslog;

import org.hibernate.search.mapper.pojo.standalone.mapping.SearchMapping;
import org.worklog.elasticsearch.CreateSessionSearchException;

import jakarta.inject.Inject;

public class AccessLogService {
    @Inject
    SearchMapping searchMapping;

    public AccessLog save(AccessLog accessLog) throws CreateSessionSearchException {
        try (var searchSession = searchMapping.createSession()) {
            searchSession.indexingPlan().add(accessLog);
            return accessLog;
        } catch (Exception e) {
            throw new CreateSessionSearchException();
        }
    }
}
