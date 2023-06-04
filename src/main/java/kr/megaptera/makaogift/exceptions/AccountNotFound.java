package kr.megaptera.makaogift.exceptions;

public class AccountNotFound extends RuntimeException {
    public AccountNotFound(String userId){
        super("Account not found (userId: " + userId + ")");
    }
}
