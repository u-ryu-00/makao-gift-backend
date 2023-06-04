package kr.megaptera.makaogift.controllers;

import kr.megaptera.makaogift.dtos.AccountDto;
import kr.megaptera.makaogift.exceptions.AccountNotFound;
import kr.megaptera.makaogift.models.Account;
import kr.megaptera.makaogift.services.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("accounts")
public class AccountController {
    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("me")
    public AccountDto account() {
        Account account = accountService.detail("a111");
        return account.toDto();
    }

    @ExceptionHandler(AccountNotFound.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String accountNotFound() {
        return "Account not Found!";
    }
}
