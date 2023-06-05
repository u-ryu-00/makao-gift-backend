package kr.megaptera.makaogift.dtos;

public class ProductDto {
    private Long id;

    private String title;

    private String company;

    private Long price;

    private String description;

    public ProductDto(Long id, String title, String company, Long price, String description) {
        this.id = id;
        this.title = title;
        this.company = company;
        this.price = price;
        this.description = description;
    }

    public Long getId(){
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
}
