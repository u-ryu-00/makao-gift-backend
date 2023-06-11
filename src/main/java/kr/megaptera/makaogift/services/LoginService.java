package kr.megaptera.makaogift.services;

import kr.megaptera.makaogift.exceptions.LoginFailed;
import kr.megaptera.makaogift.models.Account;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    public Account login(String userId, String password) {
        if (!password.equals("Aa1!!!!!")) {
            throw new LoginFailed();
        }

        Account account = Account.fake(userId);
        return account;
    }
}
