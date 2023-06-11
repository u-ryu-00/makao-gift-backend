package kr.megaptera.makaogift.services;

import kr.megaptera.makaogift.exceptions.LoginFailed;
import kr.megaptera.makaogift.models.Account;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class LoginServiceTest {
    private LoginService loginService;

    @BeforeEach
    void setUp() {
        loginService = new LoginService();
    }

    @Test
    void loginSuccess() {
        Account account = loginService.login("a111", "Aa1!!!!!");

        assertThat(account.getUserId())
                .isEqualTo("a111");
    }

    @Test
    void loginFail() {
        assertThrows(LoginFailed.class, () -> {
            loginService.login("a111", "xxx");
        });
    }
}
