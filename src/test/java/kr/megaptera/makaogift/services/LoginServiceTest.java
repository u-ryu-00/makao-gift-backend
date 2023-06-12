package kr.megaptera.makaogift.services;

import kr.megaptera.makaogift.exceptions.LoginFailed;
import kr.megaptera.makaogift.models.Account;
import kr.megaptera.makaogift.models.UserId;
import kr.megaptera.makaogift.repositories.AccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class LoginServiceTest {
    private LoginService loginService;

    private AccountRepository accountRepository;

    private PasswordEncoder passwordEncoder;

    @BeforeEach
    void setUp() {
        accountRepository = mock(AccountRepository.class);

        int saltLength = 16;
        int hashLength = 32;
        int parallelism = 4;
        int memory = 65536;
        int iterations = 3;

        passwordEncoder = new Argon2PasswordEncoder(
                saltLength, hashLength, parallelism, memory, iterations);

        loginService = new LoginService(accountRepository, passwordEncoder);
    }

    @Test
    void loginSuccess() {
        UserId userId = new UserId("a111");

        Account account = Account.fake(userId);
        account.changePassword("Aa1!!!!!", passwordEncoder);

        given(accountRepository.findByUserId(userId))
                .willReturn(Optional.of(account));

        Account found = loginService.login(userId, "Aa1!!!!!");

        assertThat(found.getUserId())
                .isEqualTo(userId);
    }

    @Test
    void loginWithIncorrectUserId() {
        assertThrows(LoginFailed.class, () -> {
            loginService.login(new UserId("xxx"), "Aa1!!!!!");
        });
    }

    @Test
    void loginWithIncorrectPassword() {
        assertThrows(LoginFailed.class, () -> {
            loginService.login(new UserId("a111"), "xxx");
        });
    }
}
