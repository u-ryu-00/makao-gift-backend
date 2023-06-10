package kr.megaptera.makaogift.dtos;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

public class PresentDto {
    private String userId;

    private Long productId;

    private String title;

    private String company;

    private String description;

    private String imageUrl;

    private Integer quantity;

    @NotBlank
    private String receiver;

    @NotBlank
    private String address;

    private String message;

    private LocalDateTime createdAt;

    public PresentDto(String userId, Long productId, String title, String company, String description, String imageUrl, Integer quantity, String receiver, String address, String message, LocalDateTime createdAt) {
        this.userId = userId;
        this.productId = productId;
        this.title = title;
        this.company = company;
        this.description = description;
        this.imageUrl = imageUrl;
        this.quantity = quantity;
        this.receiver = receiver;
        this.address = address;
        this.message = message;
        this.createdAt = createdAt;
    }

    public String getUserId() {
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
