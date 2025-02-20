package org.worklog.accesslog;

import org.hibernate.search.mapper.pojo.standalone.mapping.SearchMapping;

import jakarta.inject.Inject;

public class AccessLogService {
    @Inject
    SearchMapping searchMapping;

    public AccessLog save(AccessLog accessLog) {
        try (var searchSession = searchMapping.createSession()) {
            searchSession.indexingPlan().add(accessLog);
            return accessLog;
        } catch (Exception e) {
            // TODO: throw custom error
            throw new RuntimeException();
        }
    }
}
