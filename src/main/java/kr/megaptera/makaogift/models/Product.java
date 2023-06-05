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

    public Product() {
    }

    public Product(Long id, String title, String company, Long price, String description) {
        this.id = id;
        this.title = title;
        this.company = company;
        this.price = price;
        this.description = description;
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

    public ProductDto toDto() {
        return new ProductDto(id, title, company, price, description);
    }

    public static Product Product1() {
        return new Product(1L,"[단독각인] 캔디 글레이즈 컬러밤","입생로랑",49000L,"투명하게 녹아 맑게 빛나는 컬러 글로우 밤");
    }
}
