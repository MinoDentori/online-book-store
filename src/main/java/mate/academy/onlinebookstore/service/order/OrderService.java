package mate.academy.onlinebookstore.service.order;

import mate.academy.onlinebookstore.dto.order.OrderDto;
import mate.academy.onlinebookstore.dto.order.PlaceOrderRequestDto;
import mate.academy.onlinebookstore.dto.order.item.UpdateRequestOrderItemDto;
import org.springframework.data.domain.Pageable;

import java.util.Set;

public interface OrderService {
    Set<OrderDto> findAllOrders(Long userId, Pageable pageable);

    OrderDto placeOrder(Long userId, PlaceOrderRequestDto requestDto);

    OrderDto updateOrderStatus(Long id, UpdateRequestOrderItemDto requestDto);
}
