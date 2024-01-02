package mate.academy.onlinebookstore.dto.cart.item;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class AddToCartRequestDto {
    @Positive
    @NotNull
    private Long bookId;
    @NotNull
    private int quantity;
}
