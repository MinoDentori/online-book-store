package mate.academy.onlinebookstore.service.cart;

import mate.academy.onlinebookstore.dto.cart.AddToCartRequestDto;
import mate.academy.onlinebookstore.dto.cart.ShoppingCartDto;

public interface ShoppingCartService {
    ShoppingCartDto addToCart(AddToCartRequestDto requestDto, Long id);
}
