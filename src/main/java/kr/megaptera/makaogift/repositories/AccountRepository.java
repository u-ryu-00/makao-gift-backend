package kr.megaptera.makaogift.repositories;

import kr.megaptera.makaogift.models.Account;
import kr.megaptera.makaogift.models.UserId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByUserId(UserId userId);
}
