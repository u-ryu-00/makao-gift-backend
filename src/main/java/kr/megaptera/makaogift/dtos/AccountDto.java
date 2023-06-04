package kr.megaptera.makaogift.dtos;

public class AccountDto {
    private Long amount;
    private String userId;

    public AccountDto(String userId,Long amount) {
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
