package kr.megaptera.makaogift.services;

import kr.megaptera.makaogift.exceptions.AccountNotFound;
import kr.megaptera.makaogift.models.Account;
import kr.megaptera.makaogift.repositories.AccountRepository;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account detail(String userId) {
        return accountRepository.findByUserId(userId)
                .orElseThrow(() -> new AccountNotFound(userId));
    }
}
