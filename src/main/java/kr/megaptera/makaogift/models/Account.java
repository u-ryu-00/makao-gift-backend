package kr.megaptera.makaogift.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import kr.megaptera.makaogift.dtos.AccountDto;
import kr.megaptera.makaogift.exceptions.LackOfAmount;

@Entity
public class Account {
    @Id
    @GeneratedValue
    private Long id;

    private String userId;

    private String name;

    private Long amount;

    public Account() {
    }

    public Account(Long id, String userId, String name, Long amount) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.amount = amount;
    }

    public Long getId() {
        return id;
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

    public static Account fake(String userId) {
        return new Account(1L, userId, "내이름", 50000L);
    }

    public AccountDto toDto() {
        return new AccountDto(userId, name, amount);
    }

    public void present(Product product, Integer quantity) {
        Long totalPrice = product.getPrice() * quantity;

        if (totalPrice > this.amount) {
            throw new LackOfAmount();
        }

        this.amount -= product.getPrice() * quantity;
    }
}
