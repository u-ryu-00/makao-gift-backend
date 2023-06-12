package kr.megaptera.makaogift.models;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;

class AccountTest {
    @Test
    void present() {
        Account account = new Account(1L, new UserId("a111"), "내이름", 50000L);

        Product product = Product.fake();

        account.present(product, 1);

        assertThat(account.getAmount()).isEqualTo(1000L);
    }

    @Test
    void authenticate() {
        Account account = Account.fake("a111");

        int saltLength = 16;
        int hashLength = 32;
        int parallelism = 4;
        int memory = 65536;
        int iterations = 3;

        PasswordEncoder passwordEncoder = new Argon2PasswordEncoder(
                saltLength, hashLength, parallelism, memory, iterations);

        account.changePassword("Aa1!!!!!", passwordEncoder);

        assertThat(account.authenticate("Aa1!!!!!", passwordEncoder)).isTrue();
        assertThat(account.authenticate("xxx", passwordEncoder)).isFalse();
    }
}
