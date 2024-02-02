package org.forum.postandreplyms.exception;

public class UnauthorizedPostStatusUpdateException extends RuntimeException {
    public UnauthorizedPostStatusUpdateException(String message) {
        super(message);
    }
}
