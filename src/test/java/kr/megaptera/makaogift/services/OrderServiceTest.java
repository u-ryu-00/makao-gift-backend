package kr.megaptera.makaogift.services;

import kr.megaptera.makaogift.models.Order;
import kr.megaptera.makaogift.repositories.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class OrderServiceTest {
    OrderRepository orderRepository;
    OrderService orderService;

    @BeforeEach
    void setUp() {
        orderRepository = mock(OrderRepository.class);

        orderService = new OrderService(orderRepository);
    }

    @Test
    void list() {
        Order order = mock(Order.class);

        given(orderRepository
                .findAll())
                .willReturn(List.of(order));

        List<Order> orders =
                orderService.list();

        assertThat(orders).hasSize(1);
    }
}