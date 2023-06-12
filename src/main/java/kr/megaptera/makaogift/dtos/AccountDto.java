package kr.megaptera.makaogift.dtos;

import kr.megaptera.makaogift.models.UserId;

public class AccountDto {
    private String userId;
    private String name;
    private Long amount;


    public AccountDto(String userId, String name, Long amount) {
        this.userId = userId;
        this.name = name;
        this.amount = amount;
    }

    public String getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public Long getAmount() {
        return amount;
    }
}
