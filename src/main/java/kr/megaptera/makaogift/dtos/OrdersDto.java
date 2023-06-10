package kr.megaptera.makaogift.dtos;

import java.util.List;

public class OrdersDto {
    private final List<OrderDto> orders;

    public OrdersDto(List<OrderDto> orders) {
        this.orders = orders;
    }

    public List<OrderDto> getOrders() {
        return orders;
    }
}
