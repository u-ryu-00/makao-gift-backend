package kr.megaptera.makaogift.exceptions;

public class PasswordDoNotMatch extends RuntimeException {
    private final RuntimeException exception;

    public PasswordDoNotMatch() {
        this.exception = null;
    }

    public PasswordDoNotMatch(RuntimeException exception) {
        this.exception = exception;
    }
}
