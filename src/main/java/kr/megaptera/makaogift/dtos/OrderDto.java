package kr.megaptera.makaogift.dtos;

import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class OrderDto {
    private Long orderId;

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

    private LocalDateTime createdAt;

    public OrderDto() {
    }

    public OrderDto(Long orderId, Long productId, String title, String company, String description, String imageUrl, Integer quantity, Long totalPrice, String receiver, String address, String message, LocalDateTime createdAt) {
        this.orderId = orderId;
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
