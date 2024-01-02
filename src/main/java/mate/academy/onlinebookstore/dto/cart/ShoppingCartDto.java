package mate.academy.onlinebookstore.dto.cart;

import java.util.Set;
import lombok.Data;
import mate.academy.onlinebookstore.dto.cart.item.CartItemDto;

@Data
public class ShoppingCartDto {
    private Long id;
    private Long userId;
    private Set<CartItemDto> cartItems;
}
