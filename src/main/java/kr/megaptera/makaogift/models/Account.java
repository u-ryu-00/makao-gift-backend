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

    private Long amount;

    public Account() {
    }

    public Account(Long id, String userId, Long amount) {
        this.id = id;
        this.userId = userId;
        this.amount = amount;
    }

    public String getUserId() {
        return userId;
    }

    public Long getAmount() {
        return amount;
    }

    public static Account fake(String userId) {
        return new Account(1L, userId, 50000L);
    }

    public AccountDto toDto() {
        return new AccountDto(userId, amount);
    }

    public void present(Product product, Integer quantity) {
        Long totalPrice = product.getPrice() * quantity;

        if (totalPrice > this.amount) {
            throw new LackOfAmount();
        }

        this.amount -= product.getPrice() * quantity;
    }
}
