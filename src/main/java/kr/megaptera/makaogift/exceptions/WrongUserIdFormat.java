package kr.megaptera.makaogift.exceptions;

public class WrongUserIdFormat extends RuntimeException {
    private final RuntimeException exception;

    public WrongUserIdFormat() {
        this.exception = null;
    }

    public WrongUserIdFormat(IllegalArgumentException exception) {
        this.exception = exception;
    }
}
