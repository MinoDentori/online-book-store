package mate.academy.onlinebookstore.dto.cart.item;

import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
public class UpdateCartItemDto {
    @Min(value = 0, message = "should be positive, not null")
    private int quantity;
}
