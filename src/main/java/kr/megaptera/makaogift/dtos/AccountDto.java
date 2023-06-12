package kr.megaptera.makaogift.dtos;

public class AccountDto {
    private String userId;
    private String name;
    private Long amount;
    private String password;
    private String confirmPassword;

    public AccountDto() {
    }

    public AccountDto(String userId, String name, Long amount, String password, String confirmPassword) {
        this.userId = userId;
        this.name = name;
        this.amount = amount;
        this.password = password;
        this.confirmPassword = confirmPassword;
    }

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

    public String getPassword() {
        return password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }
}
