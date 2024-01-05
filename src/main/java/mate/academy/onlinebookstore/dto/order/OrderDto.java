package mate.academy.onlinebookstore.dto.order;

import lombok.Data;
import mate.academy.onlinebookstore.dto.order.item.OrderItemResponseDto;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

@Data
public class OrderDto {
    private Long id;
    private Long userId;
    private Set<OrderItemResponseDto> orderItems;
    private LocalDateTime orderDate;
    private BigDecimal total;
    private String status;
}
