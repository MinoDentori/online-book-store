package mate.academy.onlinebookstore.service.order;

import mate.academy.onlinebookstore.dto.order.item.OrderItemResponseDto;
import org.springframework.data.domain.Pageable;

import java.util.Set;

public interface OrderItemService {
    Set<OrderItemResponseDto> getAllOrderItemsFromOrder(Pageable pageable, Long orderId);

    OrderItemResponseDto getSpecificItemFromOrder(Long userId, Long orderId, Long itemId);
}
