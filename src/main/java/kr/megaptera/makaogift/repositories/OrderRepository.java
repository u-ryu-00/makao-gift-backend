package kr.megaptera.makaogift.repositories;

import kr.megaptera.makaogift.models.Order;
import kr.megaptera.makaogift.models.UserId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
    Page<Order> findAllByUserId(UserId userId, Pageable pageable);
}
