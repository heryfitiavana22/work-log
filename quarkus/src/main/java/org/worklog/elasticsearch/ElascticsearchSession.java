package org.worklog.elasticsearch;

import org.hibernate.search.mapper.pojo.standalone.mapping.SearchMapping;
import org.hibernate.search.mapper.pojo.standalone.session.SearchSession;
import org.worklog.elasticsearch.exception.CreateSessionSearchException;

import jakarta.inject.Inject;

public class ElascticsearchSession {
    @Inject
    SearchMapping searchMapping;

    public interface SessionCallback<T> {
        T doInSession(SearchSession searchSession) throws Exception;
    }

    public <T> T withSession(SessionCallback<T> callback) throws CreateSessionSearchException {
        try (var searchSession = searchMapping.createSession()) {
            return callback.doInSession(searchSession);
        } catch (Exception e) {
            throw new CreateSessionSearchException();
        }
    }
}
