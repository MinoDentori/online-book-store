package mate.academy.onlinebookstore.service.order.impl;

import static mate.academy.onlinebookstore.util.ErrorMessagesConstants.ORDER_ITEM_NOT_FOUND_WITH_ID;
import static mate.academy.onlinebookstore.util.ErrorMessagesConstants.ORDER_NOT_FOUND_WITH_ID;

import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import mate.academy.onlinebookstore.dto.order.item.OrderItemResponseDto;
import mate.academy.onlinebookstore.exception.EntityNotFoundException;
import mate.academy.onlinebookstore.mapper.OrderItemMapper;
import mate.academy.onlinebookstore.model.Order;
import mate.academy.onlinebookstore.model.OrderItem;
import mate.academy.onlinebookstore.repository.order.OrderItemRepository;
import mate.academy.onlinebookstore.repository.order.OrderRepository;
import mate.academy.onlinebookstore.service.order.OrderItemService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderItemServiceImpl implements OrderItemService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final OrderItemMapper orderItemMapper;

    @Override
    public Set<OrderItemResponseDto> getAllOrderItemsFromOrder(Pageable pageable, Long orderId) {
        return orderItemRepository.findAllByOrderId(orderId, pageable).stream()
                .map(orderItemMapper::toDto)
                .collect(Collectors.toSet());
    }

    @Override
    public OrderItemResponseDto getSpecificItemFromOrder(Long userId, Long orderId, Long itemId) {
        Order order = orderRepository.findByIdAndUserId(orderId, userId).orElseThrow(
                () -> new EntityNotFoundException(ORDER_NOT_FOUND_WITH_ID + orderId));

        OrderItem orderItem = order.getOrderItems().stream()
                .filter(item -> item.getId().equals(itemId))
                .findFirst()
                .orElseThrow(
                        () -> new EntityNotFoundException(ORDER_ITEM_NOT_FOUND_WITH_ID + itemId)
                );

        return orderItemMapper.toDto(orderItem);
    }
}
