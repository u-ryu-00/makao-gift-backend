package kr.megaptera.makaogift.services;

import jakarta.transaction.Transactional;
import kr.megaptera.makaogift.models.Order;
import kr.megaptera.makaogift.repositories.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class OrderService {
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<Order> list() {
        return orderRepository.findAll();
    }

    public Order detail(Long id) {
        return orderRepository.findById(id).get();
    }
}