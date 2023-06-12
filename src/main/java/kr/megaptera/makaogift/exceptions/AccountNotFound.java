package kr.megaptera.makaogift.exceptions;

import kr.megaptera.makaogift.models.UserId;

public class AccountNotFound extends RuntimeException {
    public AccountNotFound(UserId userId) {
        super("Account not found (userId: " + userId + ")");
    }
}
