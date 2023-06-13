package kr.megaptera.makaogift.services;

import jakarta.transaction.Transactional;
import kr.megaptera.makaogift.models.Product;
import kr.megaptera.makaogift.repositories.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class ProductService {
    private final ProductRepository productRepository;
    private Pageable pageable;

    public ProductService(ProductRepository productRepository, Pageable pageable) {
        this.productRepository = productRepository;
        this.pageable = pageable;
    }

    public Page<Product> list(int page) {
        Pageable pageable = PageRequest.of(page - 1, 8);
        return productRepository.findAll(pageable);
    }

    public Product detail(Long id) {
        return productRepository.findById(id).get();
    }

    public int pages() {
        Page<Product> productPage = productRepository.findAll(pageable);

        return productPage.getTotalPages();
    }
}
