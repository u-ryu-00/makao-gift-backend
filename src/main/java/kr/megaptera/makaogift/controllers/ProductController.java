package kr.megaptera.makaogift.controllers;

import kr.megaptera.makaogift.dtos.ProductDto;
import kr.megaptera.makaogift.dtos.ProductsDto;
import kr.megaptera.makaogift.models.Product;
import kr.megaptera.makaogift.services.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ProductsDto list(
            @RequestParam(required = false, defaultValue = "1") Integer page
    ) {
        List<ProductDto> productDtos =
                productService.list(page)
                        .stream()
                        .map(product -> product.toDto())
                        .collect(Collectors.toList());

        int totalPages = productService.pages();

        return new ProductsDto(productDtos, totalPages);
    }

    @GetMapping("/{id}")
    public ProductDto product(
            @PathVariable("id") Long id
    ) {
        Product product = productService.detail(id);

        return product.toDto();
    }
}
