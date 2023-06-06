package kr.megaptera.makaogift.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import kr.megaptera.makaogift.dtos.ProductDto;

@Entity
public class Product {
    @Id
    @GeneratedValue
    private Long id;

    private String title;

    private String company;

    private Long price;

    private String description;

    private String imageUrl;

    public Product() {
    }

    public Product(Long id, String title, String company, Long price, String description, String imageUrl) {
        this.id = id;
        this.title = title;
        this.company = company;
        this.price = price;
        this.description = description;
        this.imageUrl = imageUrl;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getCompany() {
        return company;
    }

    public Long getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public ProductDto toDto() {
        return new ProductDto(id, title, company, price, description, imageUrl);
    }

    public static Product fake() {
        return new Product(
                1L,
                "[단독각인] 캔디 글레이즈 컬러밤",
                "입생로랑",
                49000L,
                "투명하게 녹아 맑게 빛나는 컬러 글로우 밤",
                "https://img1.kakaocdn.net/thumb/C320x320@2x.fwebp.q82/?fname=https%3A%2F%2Fst.kakaocdn.net%2Fproduct%2Fgift%2Fproduct%2F20230516143408_834e86474204499b9fb85a2a3911ddfa.jpg"
        );
    }
}
