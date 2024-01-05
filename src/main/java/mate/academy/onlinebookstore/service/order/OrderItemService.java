package mate.academy.onlinebookstore.service.order;

import java.util.Set;
import mate.academy.onlinebookstore.dto.order.item.OrderItemResponseDto;
import org.springframework.data.domain.Pageable;

public interface OrderItemService {
    Set<OrderItemResponseDto> getAllOrderItemsFromOrder(Pageable pageable, Long orderId);

    OrderItemResponseDto getSpecificItemFromOrder(Long userId, Long orderId, Long itemId);
}
