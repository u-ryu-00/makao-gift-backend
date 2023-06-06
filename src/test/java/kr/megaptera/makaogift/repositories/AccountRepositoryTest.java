package kr.megaptera.makaogift.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
class AccountRepositoryTest {
    @Autowired
    private AccountRepository accountRepository;


}
