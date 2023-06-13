package kr.megaptera.makaogift.controllers;

import kr.megaptera.makaogift.dtos.AccountCreatedDto;
import kr.megaptera.makaogift.dtos.AccountDto;
import kr.megaptera.makaogift.dtos.ErrorDto;
import kr.megaptera.makaogift.dtos.InvalidNameLengthErrorDto;
import kr.megaptera.makaogift.dtos.PasswordDoNotMatchErrorDto;
import kr.megaptera.makaogift.dtos.UserIdAlreadyExistErrorDto;
import kr.megaptera.makaogift.dtos.WrongPasswordFormatErrorDto;
import kr.megaptera.makaogift.dtos.WrongUserIdFormatErrorDto;
import kr.megaptera.makaogift.exceptions.AccountNotFound;
import kr.megaptera.makaogift.exceptions.InvalidNameLength;
import kr.megaptera.makaogift.exceptions.PasswordDoNotMatch;
import kr.megaptera.makaogift.exceptions.UserIdAlreadyExist;
import kr.megaptera.makaogift.exceptions.WrongPasswordFormat;
import kr.megaptera.makaogift.exceptions.WrongUserIdFormat;
import kr.megaptera.makaogift.models.Account;
import kr.megaptera.makaogift.models.UserId;
import kr.megaptera.makaogift.services.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
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
    public AccountDto account(
            @RequestAttribute("userId") UserId userId
    ) {
        Account account = accountService.detail(userId);
        return account.toDto();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AccountCreatedDto register(
            @RequestBody AccountDto accountDto
    ) {
        Account account = accountService.create(accountDto);

        return account.toCreatedDto();
    }

    @ExceptionHandler(AccountNotFound.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String accountNotFound() {
        return "Account not Found!";
    }

    @ExceptionHandler(InvalidNameLength.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDto invalidNameLength() {
        return new InvalidNameLengthErrorDto();
    }

    @ExceptionHandler(WrongUserIdFormat.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDto wrongUserIdFormat() {
        return new WrongUserIdFormatErrorDto();
    }

    @ExceptionHandler(WrongPasswordFormat.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDto wrongPasswordFormat() {
        return new WrongPasswordFormatErrorDto();
    }

    @ExceptionHandler(PasswordDoNotMatch.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDto passwordDoNotMath() {
        return new PasswordDoNotMatchErrorDto();
    }

    @ExceptionHandler(UserIdAlreadyExist.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDto userIdAlreadyExist() {
        return new UserIdAlreadyExistErrorDto();
    }
}
