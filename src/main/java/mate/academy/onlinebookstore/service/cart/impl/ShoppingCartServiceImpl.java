package mate.academy.onlinebookstore.service.cart.impl;

import static mate.academy.onlinebookstore.util.ErrorMessagesConstants.BOOK_NOT_FOUND_WITH_ID;
import static mate.academy.onlinebookstore.util.ErrorMessagesConstants.CART_ITEM_NOT_FOUND_WITH_ID;
import static mate.academy.onlinebookstore.util.ErrorMessagesConstants.SHOPPING_CART_NOT_FOUND_WITH_USER_ID;
import static mate.academy.onlinebookstore.util.ErrorMessagesConstants.USER_NOT_FOUND_WITH_ID;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import mate.academy.onlinebookstore.dto.cart.ShoppingCartDto;
import mate.academy.onlinebookstore.dto.cart.item.AddToCartRequestDto;
import mate.academy.onlinebookstore.dto.cart.item.UpdateCartItemDto;
import mate.academy.onlinebookstore.exception.EntityNotFoundException;
import mate.academy.onlinebookstore.mapper.ShoppingCartMapper;
import mate.academy.onlinebookstore.model.Book;
import mate.academy.onlinebookstore.model.CartItem;
import mate.academy.onlinebookstore.model.ShoppingCart;
import mate.academy.onlinebookstore.model.User;
import mate.academy.onlinebookstore.repository.book.BookRepository;
import mate.academy.onlinebookstore.repository.cart.CartItemRepository;
import mate.academy.onlinebookstore.repository.cart.ShoppingCartRepository;
import mate.academy.onlinebookstore.repository.user.UserRepository;
import mate.academy.onlinebookstore.service.cart.ShoppingCartService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ShoppingCartServiceImpl implements ShoppingCartService {

    private final ShoppingCartRepository shoppingCartRepository;
    private final BookRepository bookRepository;
    private final UserRepository userRepository;
    private final CartItemRepository cartItemRepository;
    private final ShoppingCartMapper shoppingCartMapper;

    @Override
    @Transactional
    public ShoppingCartDto addToCart(AddToCartRequestDto requestDto, Long id) {
        Book book = bookRepository.findById(requestDto.getBookId())
                .orElseThrow(() -> new EntityNotFoundException(BOOK_NOT_FOUND_WITH_ID));
        User user = userRepository.findById(id)
                        .orElseThrow(() -> new EntityNotFoundException(
                                USER_NOT_FOUND_WITH_ID + id));
        ShoppingCart shoppingCartFromDB = shoppingCartMapper.toModel(findById(id));

        CartItem cartItem = new CartItem();
        cartItem.setQuantity(requestDto.getQuantity());
        cartItem.setBook(book);
        cartItem.setShoppingCart(shoppingCartFromDB);
        cartItemRepository.save(cartItem);
        shoppingCartFromDB.getCartItems().add(cartItem);

        return shoppingCartMapper.toDto(shoppingCartFromDB);
    }

    @Override
    public ShoppingCartDto findById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(USER_NOT_FOUND_WITH_ID + id));
        ShoppingCart shoppingCartFromDB = shoppingCartRepository.findByUserId(id)
                .orElseGet(() -> {
                    ShoppingCart shoppingCart = new ShoppingCart();
                    shoppingCart.setUser(user);
                    shoppingCartRepository.save(shoppingCart);
                    return shoppingCart;
                });
        return shoppingCartMapper.toDto(shoppingCartFromDB);
    }

    @Override
    @Transactional
    public ShoppingCartDto updateItem(Long userId, Long cartItemId, UpdateCartItemDto requestDto) {
        ShoppingCart shoppingCart = shoppingCartRepository.findByUserId(userId)
                .orElseThrow(() -> new EntityNotFoundException(
                        SHOPPING_CART_NOT_FOUND_WITH_USER_ID + userId));

        CartItem cartItem = cartItemRepository.findByIdAndShoppingCartId(cartItemId, shoppingCart.getId())
                .orElseThrow(() -> new EntityNotFoundException(CART_ITEM_NOT_FOUND_WITH_ID + cartItemId));

        cartItem.setQuantity(requestDto.getQuantity());
        cartItemRepository.save(cartItem);
        return shoppingCartMapper.toDto(cartItem.getShoppingCart());
    }
}
