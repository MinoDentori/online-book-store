package mate.academy.onlinebookstore.service.cart;

import mate.academy.onlinebookstore.dto.cart.item.AddToCartRequestDto;
import mate.academy.onlinebookstore.dto.cart.ShoppingCartDto;
import mate.academy.onlinebookstore.dto.cart.item.UpdateCartItemDto;

public interface ShoppingCartService {
    ShoppingCartDto addToCart(AddToCartRequestDto requestDto, Long id);

    ShoppingCartDto findById(Long id);

    ShoppingCartDto updateItem(Long userId, Long id, UpdateCartItemDto requestDto);
}
