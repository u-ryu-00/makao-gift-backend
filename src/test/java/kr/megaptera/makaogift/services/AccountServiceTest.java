package kr.megaptera.makaogift.services;

import kr.megaptera.makaogift.models.Account;
import kr.megaptera.makaogift.repositories.AccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class AccountServiceTest {
    AccountService accountService;

    AccountRepository accountRepository;

    @BeforeEach
    void setUp() {
        accountRepository = mock(AccountRepository.class);

        given(accountRepository.findByUserId(anyString()))
                .willReturn(Optional.of(Account.fake("a111")));

        accountService = new AccountService(accountRepository);
    }

    @Test
    void account() {
        Account account = accountService.detail("a111");

        verify(accountRepository).findByUserId("a111");

        assertThat(account.getUserId()).isEqualTo("a111");
    }

}
