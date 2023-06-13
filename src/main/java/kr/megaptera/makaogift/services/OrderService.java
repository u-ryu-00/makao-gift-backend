package kr.megaptera.makaogift.services;

import jakarta.transaction.Transactional;
import kr.megaptera.makaogift.models.Order;
import kr.megaptera.makaogift.models.UserId;
import kr.megaptera.makaogift.repositories.OrderRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class OrderService {
    private final OrderRepository orderRepository;
    private Pageable pageable;

    public OrderService(OrderRepository orderRepository, Pageable pageable) {
        this.orderRepository = orderRepository;
        this.pageable = pageable;
    }

    public Page<Order> list(UserId userId, int page) {
        Pageable pageable = PageRequest.of(page - 1, 8);
        return orderRepository.findAllByUserId(userId, pageable);
    }

    public Order detail(Long id) {
        return orderRepository.findById(id).get();
    }

    public int pages(UserId userId) {
        Page<Order> orderPage = orderRepository.findAllByUserId(userId, pageable);

        return orderPage.getTotalPages();
    }
}
