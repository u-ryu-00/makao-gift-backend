package kr.megaptera.makaogift.dtos;

public class AccountDto {
    private String userId;
    private Long amount;

    public AccountDto(String userId, Long amount) {
        this.userId = userId;
        this.amount = amount;
    }

    public String getUserId() {
        return userId;
    }

    public Long getAmount() {
        return amount;
    }
}
