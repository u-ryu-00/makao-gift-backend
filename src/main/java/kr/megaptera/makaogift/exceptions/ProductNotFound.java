package kr.megaptera.makaogift.exceptions;

public class ProductNotFound extends RuntimeException {
    public ProductNotFound(Long id) {
        super("Product Not Found (id: " + id + ")");
    }
}
