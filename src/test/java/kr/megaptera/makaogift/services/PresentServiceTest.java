package kr.megaptera.makaogift.services;

import kr.megaptera.makaogift.models.Account;
import kr.megaptera.makaogift.models.Product;
import kr.megaptera.makaogift.repositories.AccountRepository;
import kr.megaptera.makaogift.repositories.OrderRepository;
import kr.megaptera.makaogift.repositories.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class PresentServiceTest {
    private PresentService presentService;

    private AccountRepository accountRepository;

    private ProductRepository productRepository;

    private OrderRepository orderRepository;

    @BeforeEach
    void setUp() {
        accountRepository = mock(AccountRepository.class);

        productRepository = mock(ProductRepository.class);

        orderRepository = mock(OrderRepository.class);

        presentService = new PresentService(accountRepository, productRepository, orderRepository);
    }

    @Test
    void present() {
        String userId = "a111";
        Long productId = 1L;
        String title = "[단독각인] 캔디 글레이즈 컬러밤";
        String company = "입생로랑";
        String description = "투명하게 녹아 맑게 빛나는 컬러 글로우 밤";
        String imageUrl = "https://img1.kakaocdn.net/thumb/C320x320@2x.fwebp.q82/?fname=https%3A%2F%2Fst.kakaocdn.net%2Fproduct%2Fgift%2Fproduct%2F20230516143408_834e86474204499b9fb85a2a3911ddfa.jpg";
        Integer quantity = 1;
        String receiver = "이름";
        String address = "주소";
        String message = "메시지";
        LocalDateTime createdAt = LocalDateTime.now();

        Account account = new Account(1L, "a111", 50000L);

        Product product = new Product(
                1L,
                "[단독각인] 캔디 글레이즈 컬러밤",
                "입생로랑",
                49000L,
                "투명하게 녹아 맑게 빛나는 컬러 글로우 밤",
                "https://img1.kakaocdn.net/thumb/C320x320@2x.fwebp.q82/?fname=https%3A%2F%2Fst.kakaocdn.net%2Fproduct%2Fgift%2Fproduct%2F20230516143408_834e86474204499b9fb85a2a3911ddfa.jpg"
        );

        given(accountRepository.findByUserId(userId)).willReturn(Optional.of(account));

        given(productRepository.findByProductId(productId)).willReturn(Optional.of(product));

        presentService.present(userId, productId, title, company, description, imageUrl, quantity, receiver, address, message, createdAt);

        assertThat(account.getAmount()).isEqualTo(50000L - 49000L * quantity);

        verify(orderRepository).save(any());
    }
}
