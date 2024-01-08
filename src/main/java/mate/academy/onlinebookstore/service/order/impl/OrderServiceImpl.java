package mate.academy.onlinebookstore.service.order.impl;

import static mate.academy.onlinebookstore.util.ErrorMessagesConstants.ORDER_NOT_FOUND_WITH_ID;
import static mate.academy.onlinebookstore.util.ErrorMessagesConstants.SHOPPING_CART_NOT_FOUND_WITH_USER_ID;
import static mate.academy.onlinebookstore.util.ErrorMessagesConstants.USER_NOT_FOUND_WITH_ID;

import jakarta.transaction.Transactional;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import mate.academy.onlinebookstore.dto.order.OrderDto;
import mate.academy.onlinebookstore.dto.order.PlaceOrderRequestDto;
import mate.academy.onlinebookstore.dto.order.item.UpdateRequestOrderItemDto;
import mate.academy.onlinebookstore.exception.EntityNotFoundException;
import mate.academy.onlinebookstore.mapper.OrderMapper;
import mate.academy.onlinebookstore.model.Book;
import mate.academy.onlinebookstore.model.CartItem;
import mate.academy.onlinebookstore.model.Order;
import mate.academy.onlinebookstore.model.OrderItem;
import mate.academy.onlinebookstore.model.ShoppingCart;
import mate.academy.onlinebookstore.model.User;
import mate.academy.onlinebookstore.repository.cart.ShoppingCartRepository;
import mate.academy.onlinebookstore.repository.order.OrderItemRepository;
import mate.academy.onlinebookstore.repository.order.OrderRepository;
import mate.academy.onlinebookstore.repository.user.UserRepository;
import mate.academy.onlinebookstore.service.order.OrderService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final ShoppingCartRepository shoppingCartRepository;
    private final OrderItemRepository orderItemRepository;
    private final OrderMapper orderMapper;

    @Override
    public List<OrderDto> findAllOrders(Long userId, Pageable pageable) {
        return orderMapper.listToDto(orderRepository.findOrdersByUserId(userId, pageable));
    }

    @Override
    @Transactional
    public OrderDto placeOrder(Long userId, PlaceOrderRequestDto requestDto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException(USER_NOT_FOUND_WITH_ID + userId));

        ShoppingCart shoppingCart = shoppingCartRepository.findByUserId(userId)
                .orElseThrow(() -> new EntityNotFoundException(
                        SHOPPING_CART_NOT_FOUND_WITH_USER_ID + userId));
        BigDecimal total = calculateTotalForShoppingCart(shoppingCart);

        Order order = new Order();
        order.setUser(user);
        order.setStatus(Order.Status.PENDING);
        order.setShippingAddress(requestDto.shippingAddress());
        order.setTotal(total);

        order = orderRepository.save(order);

        Set<OrderItem> orderItems = getOrderItemsFromShoppingCart(shoppingCart, order);
        order.setOrderItems(orderItems);
        return orderMapper.toDto(order);
    }

    private Set<OrderItem> getOrderItemsFromShoppingCart(ShoppingCart shoppingCart, Order order) {
        Set<OrderItem> orderItems = new HashSet<>(shoppingCart.getCartItems().size());
        for (CartItem cartItem : shoppingCart.getCartItems()) {
            Book book = cartItem.getBook();

            OrderItem orderItem = new OrderItem();
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setBook(book);
            orderItem.setOrder(order);
            orderItem.setPrice(book.getPrice());
            orderItemRepository.save(orderItem);
            orderItems.add(orderItem);
        }
        return orderItems;
    }

    private BigDecimal calculateTotalForShoppingCart(ShoppingCart shoppingCart) {
        BigDecimal total = BigDecimal.ZERO;
        for (CartItem cartItem : shoppingCart.getCartItems()) {
            BigDecimal itemPrice = cartItem.getBook().getPrice()
                    .multiply(BigDecimal.valueOf(cartItem.getQuantity()));
            total = total.add(itemPrice);
        }
        return total;
    }

    @Override
    public OrderDto updateOrderStatus(Long id, UpdateRequestOrderItemDto requestDto) {
        Order order = orderRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(ORDER_NOT_FOUND_WITH_ID + id)
        );
        order.setStatus(requestDto.getStatus());

        return orderMapper.toDto(orderRepository.save(order));
    }
}
