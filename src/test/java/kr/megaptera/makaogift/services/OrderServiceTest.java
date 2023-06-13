package kr.megaptera.makaogift.services;

import kr.megaptera.makaogift.repositories.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.data.domain.Pageable;

import static org.mockito.Mockito.mock;

class OrderServiceTest {
    OrderRepository orderRepository;
    OrderService orderService;
    Pageable pageable;

    @BeforeEach
    void setUp() {
        orderRepository = mock(OrderRepository.class);

        orderService = new OrderService(orderRepository, pageable);
    }

//    @Test
//    void list() {
//        UserId userId = new UserId("a111");
//
//        Order order = mock(Order.class);
//
//        given(orderRepository
//                .findAllByUserId(eq(userId)))
//                .willReturn((Page<Order>) List.of(order));
//
//        List<Order> orders =
//                orderService.list(userId);
//
//        assertThat(orders).hasSize(1);
//    }
}
