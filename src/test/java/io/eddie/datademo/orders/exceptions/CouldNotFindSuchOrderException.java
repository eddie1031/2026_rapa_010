package io.eddie.datademo.orders.exceptions;

public class CouldNotFindSuchOrderException extends RuntimeException {

    private static final String MESSAGE = "해당 주문은 존재하지 않습니다.";

    public CouldNotFindSuchOrderException() {
        super(MESSAGE);
    }

    public CouldNotFindSuchOrderException(Throwable cause) {
        super(MESSAGE, cause);
    }
}
