package kr.megaptera.makaogift.exceptions;

public class InvalidNameLength extends RuntimeException {
    private final RuntimeException exception;

    public InvalidNameLength() {
        this.exception = null;
    }

    public InvalidNameLength(IllegalArgumentException exception) {
        this.exception = exception;
    }
}
