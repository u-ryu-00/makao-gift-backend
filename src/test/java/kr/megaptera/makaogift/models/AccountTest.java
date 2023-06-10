package kr.megaptera.makaogift.models;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class AccountTest {
    @Test
    void present() {
        Account account = new Account(1L, "a111", 50000L);

        Product product = Product.fake();
        
        account.present(product, 1);

        assertThat(account.getAmount()).isEqualTo(1000L);
    }
}
