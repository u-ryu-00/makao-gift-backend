package kr.megaptera.makaogift.services;

import jakarta.transaction.Transactional;
import kr.megaptera.makaogift.exceptions.AccountNotFound;
import kr.megaptera.makaogift.exceptions.ProductNotFound;
import kr.megaptera.makaogift.models.Account;
import kr.megaptera.makaogift.models.Order;
import kr.megaptera.makaogift.models.Product;
import kr.megaptera.makaogift.repositories.AccountRepository;
import kr.megaptera.makaogift.repositories.OrderRepository;
import kr.megaptera.makaogift.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Transactional
public class PresentService {
    private final AccountRepository accountRepository;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;

    public PresentService(AccountRepository accountRepository, ProductRepository productRepository, OrderRepository orderRepository) {
        this.accountRepository = accountRepository;
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
    }

    public Order present(String userId, Long productId, String title, String company, String description, String imageUrl, Integer quantity,
                         String receiver, String address, String message, LocalDateTime createdAt) {
        Account account = accountRepository.findByUserId(userId)
                .orElseThrow(() -> new AccountNotFound(userId));

        Product product = productRepository.findByProductId(productId)
                .orElseThrow(() -> new ProductNotFound(productId));

        account.present(product, quantity);

        Long totalPrice = product.getPrice() * quantity;

        Order order = new Order(null, userId, productId, title,
                company, description, imageUrl,
                quantity, totalPrice, receiver, address, message, createdAt);

        orderRepository.save(order);

        return order;
    }
}
