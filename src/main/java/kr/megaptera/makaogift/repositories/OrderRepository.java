package kr.megaptera.makaogift.repositories;

import kr.megaptera.makaogift.models.Order;
import kr.megaptera.makaogift.models.UserId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAllByUserId(UserId userId);
}
