package mate.academy.onlinebookstore.dto.cart;

import lombok.Data;
import java.util.Set;

@Data
public class ShoppingCartDto {
    private Long id;
    private Long userId;
    private Set<CartItemDto> cartItems;
}
