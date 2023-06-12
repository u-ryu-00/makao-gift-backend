package kr.megaptera.makaogift.models;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import kr.megaptera.makaogift.dtos.OrderDto;
import kr.megaptera.makaogift.dtos.PresentResultDto;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "ORDER_HISTORY")
public class Order {
    @Id
    @GeneratedValue
    private Long orderId;

    @Embedded
    private UserId userId;

    private Long productId;

    private String title;

    private String company;

    private String description;

    private String imageUrl;

    private Integer quantity;

    private Long totalPrice;

    private String receiver;

    private String address;

    private String message;

    @CreationTimestamp
    private LocalDateTime createdAt;

    public Order() {
    }

    public Order(Long orderId, UserId userId, Long productId, String title, String company, String description, String imageUrl, Integer quantity, Long totalPrice, String receiver, String address, String message, LocalDateTime createdAt) {
        this.orderId = orderId;
        this.userId = userId;
        this.productId = productId;
        this.title = title;
        this.company = company;
        this.description = description;
        this.imageUrl = imageUrl;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.receiver = receiver;
        this.address = address;
        this.message = message;
        this.createdAt = createdAt;
    }

    public Long getOrderId() {
        return orderId;
    }

    public UserId getUserId() {
        return userId;
    }

    public Long getProductId() {
        return productId;
    }

    public String getTitle() {
        return title;
    }

    public String getCompany() {
        return company;
    }

    public String getDescription() {
        return description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Long getTotalPrice() {
        return totalPrice;
    }

    public String getReceiver() {
        return receiver;
    }

    public String getAddress() {
        return address;
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime createdAt() {
        return createdAt;
    }

    public PresentResultDto toPresentResultDto() {
        return new PresentResultDto(orderId, productId, quantity);
    }

    public OrderDto toDto() {
        return new OrderDto(orderId, productId, title, company, description, imageUrl, quantity, totalPrice, receiver, address, message, createdAt);
    }

    public static Order fake() {
        LocalDateTime createdAt = LocalDateTime.now();
        return new Order(1L, new UserId("a111"), 1L, "바디워시", "탬버린즈", "물에 닿는 순간 풍성한 거품으로 변해 피부를 부드럽게 씻어내어 자연의 향으로 감싸줍니다.", "https://img1.kakaocdn.net/thumb/C320x320@2x.fwebp.q82/?fname=https%3A%2F%2Fst.kakaocdn.net%2Fproduct%2Fgift%2Fproduct%2F20221124180904_d7730ce8710a45a084a62ee6c1f56766.jpg\t", 2, 20000L, "이름", "주소", "메시지", createdAt);
    }
}
