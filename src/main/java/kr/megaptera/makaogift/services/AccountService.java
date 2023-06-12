package kr.megaptera.makaogift.services;

import jakarta.transaction.Transactional;
import kr.megaptera.makaogift.dtos.AccountDto;
import kr.megaptera.makaogift.exceptions.AccountNotFound;
import kr.megaptera.makaogift.models.Account;
import kr.megaptera.makaogift.models.UserId;
import kr.megaptera.makaogift.repositories.AccountRepository;
import org.apache.catalina.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class AccountService {
    private final PasswordEncoder passwordEncoder;
    private final AccountRepository accountRepository;

    public AccountService(PasswordEncoder passwordEncoder,
                          AccountRepository accountRepository) {
        this.passwordEncoder = passwordEncoder;
        this.accountRepository = accountRepository;
    }

    public Account detail(UserId userId) {
        return accountRepository.findByUserId(userId)
                .orElseThrow(() -> new AccountNotFound(userId));
    }

    public Account create(AccountDto accountDto) {
        String userIdString = accountDto.getUserId();

        UserId userId = new UserId(userIdString);

        Account account = new Account(userId, accountDto.getName(), accountDto.getAmount());

        account.changePassword(accountDto.getPassword(), passwordEncoder);
        account.changePassword(accountDto.getConfirmPassword(), passwordEncoder);

        accountRepository.save(account);

        return account;
    }
}
