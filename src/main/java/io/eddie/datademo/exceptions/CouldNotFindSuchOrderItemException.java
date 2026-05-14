package io.eddie.datademo.exceptions;

public class CouldNotFindSuchOrderItemException extends RuntimeException {

    private static final String MESSAGE = "해당 주문내용은 존재하지 않습니다";

    public CouldNotFindSuchOrderItemException() {
        super(MESSAGE);
    }

    public CouldNotFindSuchOrderItemException(Throwable cause) {
        super(MESSAGE, cause);
    }

}
