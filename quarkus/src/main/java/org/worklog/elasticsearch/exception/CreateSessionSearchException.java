package org.worklog.elasticsearch.exception;

public class CreateSessionSearchException extends Exception {
    public CreateSessionSearchException() {
        super("Failed to create search session");
    }
}
