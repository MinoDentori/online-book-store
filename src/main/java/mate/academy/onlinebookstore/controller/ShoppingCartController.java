package mate.academy.onlinebookstore.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import mate.academy.onlinebookstore.dto.cart.ShoppingCartDto;
import mate.academy.onlinebookstore.dto.cart.item.AddToCartRequestDto;
import mate.academy.onlinebookstore.dto.cart.item.UpdateCartItemDto;
import mate.academy.onlinebookstore.model.User;
import mate.academy.onlinebookstore.service.cart.CartItemService;
import mate.academy.onlinebookstore.service.cart.ShoppingCartService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Shopping cart management", description = "Endpoints for managing shopping cart")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/cart")
public class ShoppingCartController {

    private final ShoppingCartService shoppingCartService;
    private final CartItemService cartItemService;

    @PostMapping
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    @Operation(summary = "Add an item to shopping cart",
            description = "Add an item to shopping cart")
    public ShoppingCartDto addToCart(
            @RequestBody AddToCartRequestDto requestDto,
            Authentication authentication
    ) {
        User user = (User) authentication.getPrincipal();
        return shoppingCartService.addToCart(requestDto, user.getId());
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    @Operation(summary = "Get a shopping cart with items",
            description = "Get a shopping cart with items")
    public ShoppingCartDto getShoppingCart(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        return shoppingCartService.findById(user.getId());
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @PutMapping("/cart-items/{id}")
    @Operation(summary = "Update an item in shopping cart",
            description = "Update an item in shopping cart by item's id")
    public ShoppingCartDto updateItem(
            Authentication authentication,
            @PathVariable Long id,
            @RequestBody @Valid UpdateCartItemDto requestDto
    ) {
        User user = (User) authentication.getPrincipal();
        return shoppingCartService.updateItem(user.getId(), id, requestDto);
    }

    @DeleteMapping("/cart-items/{id}")
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    @Operation(summary = "Delete an item from shopping cart",
            description = "Delete an item from shopping cart")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    private void deleteCartItemById(@PathVariable Long id) {
        cartItemService.deleteById(id);
    }
}
