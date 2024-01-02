package mate.academy.onlinebookstore.controller;

import lombok.RequiredArgsConstructor;
import mate.academy.onlinebookstore.dto.cart.AddToCartRequestDto;
import mate.academy.onlinebookstore.dto.cart.ShoppingCartDto;
import mate.academy.onlinebookstore.model.User;
import mate.academy.onlinebookstore.service.cart.ShoppingCartService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/cart")
public class ShoppingCartController {

    private final ShoppingCartService shoppingCartService;
    @PostMapping
    public ShoppingCartDto addToCart(
            @RequestBody AddToCartRequestDto requestDto,
            Authentication authentication
    ) {
        User user = (User) authentication.getPrincipal();
        shoppingCartService.addToCart(requestDto, user.getId());
        return null;
    }
}
