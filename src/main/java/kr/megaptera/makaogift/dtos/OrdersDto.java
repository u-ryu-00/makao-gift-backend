package kr.megaptera.makaogift.dtos;

import java.util.List;

public class OrdersDto {
    private final List<OrderDto> orders;
    private int totalPages;

    public OrdersDto(List<OrderDto> orders) {
        this.orders = orders;
    }

    public OrdersDto(List<OrderDto> orders, int totalPages) {
        this.orders = orders;
        this.totalPages = totalPages;
    }

    public List<OrderDto> getOrders() {
        return orders;
    }

    public int getTotalPages() {
        return totalPages;
    }
}
