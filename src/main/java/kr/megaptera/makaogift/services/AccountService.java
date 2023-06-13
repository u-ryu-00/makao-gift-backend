package kr.megaptera.makaogift.services;

import jakarta.transaction.Transactional;
import kr.megaptera.makaogift.dtos.AccountDto;
import kr.megaptera.makaogift.dtos.WrongUserIdFormatErrorDto;
import kr.megaptera.makaogift.exceptions.AccountNotFound;
import kr.megaptera.makaogift.exceptions.InvalidNameLength;
import kr.megaptera.makaogift.exceptions.PasswordDoNotMatch;
import kr.megaptera.makaogift.exceptions.UserIdAlreadyExist;
import kr.megaptera.makaogift.exceptions.WrongPasswordFormat;
import kr.megaptera.makaogift.exceptions.WrongUserIdFormat;
import kr.megaptera.makaogift.models.Account;
import kr.megaptera.makaogift.models.UserId;
import kr.megaptera.makaogift.repositories.AccountRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

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

        String nameRegex = "^[가-힣]{3,7}$";
        if (!accountDto.getName().matches(nameRegex)) {
            throw new InvalidNameLength();
        }

        String userIdRegex = "^[a-z0-9]{4,16}$";
        if (!accountDto.getUserId().matches(userIdRegex)) {
            throw new WrongUserIdFormat();
        }

        String passwordRegex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$";
        if (!accountDto.getPassword().matches(passwordRegex)) {
            throw new WrongPasswordFormat();
        }

        if (!accountDto.getPassword().equals(accountDto.getConfirmPassword())) {
            throw new PasswordDoNotMatch();
        }

        Optional<Account> existedAccount = accountRepository.findByUserId(userId);
        existedAccount.ifPresent(account1 -> {
            throw new UserIdAlreadyExist();
        });

        account.changePassword(accountDto.getPassword(), passwordEncoder);
        account.changePassword(accountDto.getConfirmPassword(), passwordEncoder);

        accountRepository.save(account);

        return account;
    }
}
