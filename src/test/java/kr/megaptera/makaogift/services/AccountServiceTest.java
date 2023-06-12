package kr.megaptera.makaogift.services;

import kr.megaptera.makaogift.models.Account;
import kr.megaptera.makaogift.models.UserId;
import kr.megaptera.makaogift.repositories.AccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
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

        given(accountRepository.findByUserId(any()))
                .willReturn(Optional.of(Account.fake("a111")));

        accountService = new AccountService(accountRepository);
    }

    @Test
    void account() {
        UserId userId = new UserId("a111");
        Account account = accountService.detail(userId);

        verify(accountRepository).findByUserId(userId);

        assertThat(account.getUserId()).isEqualTo(userId);
    }
}
