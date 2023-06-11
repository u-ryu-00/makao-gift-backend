package kr.megaptera.makaogift.dtos;

public class LoginResultDto {
    private final String accessToken;

    private final Long amount;

    public LoginResultDto(String accessToken, Long amount) {
        this.accessToken = accessToken;
        this.amount = amount;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public Long getAmount() {
        return amount;
    }
}
