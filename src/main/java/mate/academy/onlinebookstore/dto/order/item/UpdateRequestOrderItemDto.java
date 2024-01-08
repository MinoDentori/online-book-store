package mate.academy.onlinebookstore.dto.order.item;

import lombok.Data;
import mate.academy.onlinebookstore.model.Order;

@Data
public class UpdateRequestOrderItemDto {
    private Order.Status status;
}
