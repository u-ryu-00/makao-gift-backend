package kr.megaptera.makaogift.services;

import kr.megaptera.makaogift.models.Product;
import kr.megaptera.makaogift.repositories.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class ProductServiceTest {
    ProductRepository productRepository;
    ProductService productService;
    Pageable pageable;

    @BeforeEach
    void setUp() {
        productRepository = mock(ProductRepository.class);
        pageable = PageRequest.of(0, 10);

        productService = new ProductService(productRepository, pageable);
    }

    @Test
    void list() {
        Product product = mock(Product.class);

        given(productRepository
                .findAll(any(Pageable.class)))
                .willReturn(new PageImpl<>(List.of(product)));

        List<Product> products = productService.list(1).getContent();

        assertThat(products).hasSize(1);
    }
}
