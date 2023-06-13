package kr.megaptera.makaogift.exceptions;

import kr.megaptera.makaogift.dtos.UserIdAlreadyExistErrorDto;

public class UserIdAlreadyExist extends RuntimeException {
    private final RuntimeException exception;

    public UserIdAlreadyExist() {
        this.exception = null;
    }

    public UserIdAlreadyExist(RuntimeException exception) {
        this.exception = exception;
    }
}
