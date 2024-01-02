package mate.academy.onlinebookstore.dto.cart;

import lombok.Data;

@Data
public class AddToCartRequestDto {
    private Long bookId;
    private int quantity;
}
