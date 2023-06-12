package kr.megaptera.makaogift.models;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import kr.megaptera.makaogift.dtos.AccountDto;
import kr.megaptera.makaogift.exceptions.LackOfAmount;
import org.springframework.security.crypto.password.PasswordEncoder;

@Entity
public class Account {
    @Id
    @GeneratedValue
    private Long id;

    @Embedded
    private UserId userId;

    private String encodedPassword;

    private String name;

    private Long amount;

    public Account() {
    }

    public Account(Long id, UserId userId, String name, Long amount) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.amount = amount;
    }

    public Long getId() {
        return id;
    }

    public UserId getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public Long getAmount() {
        return amount;
    }

    public void changePassword(String password,
                               PasswordEncoder passwordEncoder) {
        encodedPassword = passwordEncoder.encode(password);
    }

    public boolean authenticate(String password,
                                PasswordEncoder passwordEncoder) {
        return passwordEncoder.matches(password, encodedPassword);
    }

    public static Account fake(String userId) {
        return new Account(1L, new UserId(userId), "내이름", 50000L);
    }

    public static Account fake(UserId userId) {
        return Account.fake(userId.value());
    }

    public AccountDto toDto() {
        return new AccountDto(userId.value(), name, amount);
    }

    public void present(Product product, Integer quantity) {
        Long totalPrice = product.getPrice() * quantity;

        if (totalPrice > this.amount) {
            throw new LackOfAmount();
        }

        this.amount -= product.getPrice() * quantity;
    }
}
