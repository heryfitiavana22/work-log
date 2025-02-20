package org.worklog.elasticsearch;

public class CreateSessionSearchException extends Exception {
    public CreateSessionSearchException() {
        super("Failed to create search session");
    }
}
