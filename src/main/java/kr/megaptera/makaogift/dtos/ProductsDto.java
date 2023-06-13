package kr.megaptera.makaogift.dtos;

import java.util.List;

public class ProductsDto {
    private final List<ProductDto> products;
    private int totalPages;

    public ProductsDto(List<ProductDto> products) {
        this.products = products;
    }

    public ProductsDto(List<ProductDto> products, int totalPages) {
        this.products = products;
        this.totalPages = totalPages;
    }

    public List<ProductDto> getProducts() {
        return products;
    }

    public int getTotalPages() {
        return totalPages;
    }
}
