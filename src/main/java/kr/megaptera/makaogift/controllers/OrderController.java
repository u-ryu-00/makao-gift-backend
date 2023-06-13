package kr.megaptera.makaogift.controllers;

import jakarta.validation.Valid;
import kr.megaptera.makaogift.dtos.OrderDto;
import kr.megaptera.makaogift.dtos.OrdersDto;
import kr.megaptera.makaogift.dtos.PresentDto;
import kr.megaptera.makaogift.dtos.PresentResultDto;
import kr.megaptera.makaogift.models.Order;
import kr.megaptera.makaogift.models.UserId;
import kr.megaptera.makaogift.services.OrderService;
import kr.megaptera.makaogift.services.PresentService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("orders")
public class OrderController {
    private final OrderService orderService;
    private final PresentService presentService;

    public OrderController(OrderService orderService, PresentService presentService) {
        this.orderService = orderService;
        this.presentService = presentService;
    }

    @GetMapping
    public OrdersDto list(
            @RequestAttribute("userId") UserId userId,
            @RequestParam(required = false, defaultValue = "1") Integer page
    ) {
        List<OrderDto> orderDtos =
                orderService.list(userId, page)
                        .stream()
                        .map(order -> order.toDto())
                        .collect(Collectors.toList());
        int totalPages = orderService.pages(userId);

        return new OrdersDto(orderDtos, totalPages);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PresentResultDto present(
            @Valid @RequestBody PresentDto presentDto
    ) {
        Order order = presentService.present(
                presentDto.getUserId(),
                presentDto.getProductId(),
                presentDto.getTitle(),
                presentDto.getCompany(),
                presentDto.getDescription(),
                presentDto.getImageUrl(),
                presentDto.getQuantity(),
                presentDto.getReceiver(),
                presentDto.getAddress(),
                presentDto.getMessage(),
                presentDto.getCreatedAt()
        );
        return order.toPresentResultDto();
    }

    @GetMapping("/{id}")
    public OrderDto product(
            @PathVariable("id") Long id
    ) {
        Order order = orderService.detail(id);

        return order.toDto();
    }
}
