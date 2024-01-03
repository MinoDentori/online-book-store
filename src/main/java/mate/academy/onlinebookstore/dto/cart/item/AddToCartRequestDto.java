package mate.academy.onlinebookstore.dto.cart.item;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class AddToCartRequestDto {
    @NotNull
    private Long bookId;
    @Positive
    private int quantity;
}
