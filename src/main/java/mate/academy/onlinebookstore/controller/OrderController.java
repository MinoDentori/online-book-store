package mate.academy.onlinebookstore.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import mate.academy.onlinebookstore.dto.order.OrderDto;
import mate.academy.onlinebookstore.dto.order.PlaceOrderRequestDto;
import mate.academy.onlinebookstore.dto.order.item.OrderItemResponseDto;
import mate.academy.onlinebookstore.dto.order.item.UpdateRequestOrderItemDto;
import mate.academy.onlinebookstore.model.User;
import mate.academy.onlinebookstore.service.order.OrderItemService;
import mate.academy.onlinebookstore.service.order.OrderService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Order management", description = "Endpoints for managing orders")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/orders")
public class OrderController {
    private final OrderService orderService;
    private final OrderItemService orderItemService;

    @GetMapping
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    @Operation(summary = "Retrieve user's order history",
            description = "Retrieve user's order history")
    public Set<OrderDto> findAllOrders(Authentication authentication,
                                               Pageable pageable) {
        User user = (User) authentication.getPrincipal();
        return orderService.findAllOrders(user.getId(), pageable);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    @Operation(summary = "Create an Order",
            description = "Create an Order")
    public OrderDto placeOrder(@RequestBody @Valid PlaceOrderRequestDto requestDto,
                               Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        return orderService.placeOrder(user.getId(), requestDto);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/{id}")
    @Operation(summary = "Update an order status",
            description = "Updates status for specific order")
    public OrderDto updateOrderStatus(
            @PathVariable Long id,
            @RequestBody @Valid UpdateRequestOrderItemDto requestDto
    ) {
        return orderService.updateOrderStatus(id, requestDto);
    }

    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    @GetMapping("/{orderId}/items")
    @Operation(summary = "Retrieve all OrderItem within an order",
            description = "Retrieve all OrderItem within an order")
    public Set<OrderItemResponseDto> getAllOrderItemsForSpecificOrder(
            Pageable pageable,
            @PathVariable Long orderId
    ) {
        return orderItemService.getAllOrderItemsFromOrder(pageable, orderId);
    }

    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    @GetMapping("/{orderId}/items/{itemId}")
    @Operation(summary = "Retrieve a specific OrderItem within an order",
            description = "Retrieve a specific OrderItem within an order")
    public OrderItemResponseDto getSpecificItemFromOrder(
            Authentication authentication,
            @PathVariable Long orderId,
            @PathVariable Long itemId
    ) {
        User user = (User) authentication.getPrincipal();
        return orderItemService.getSpecificItemFromOrder(user.getId(), orderId, itemId);
    }

}
