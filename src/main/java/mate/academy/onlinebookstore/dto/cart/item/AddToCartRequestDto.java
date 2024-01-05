package mate.academy.onlinebookstore.dto.cart.item;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AddToCartRequestDto {
    @NotNull
    private Long bookId;
    @Min(value = 1, message = "should be at list 1")
    private int quantity;
}
