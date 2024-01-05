package mate.academy.onlinebookstore.mapper;

import mate.academy.onlinebookstore.config.MapperConfig;
import mate.academy.onlinebookstore.dto.order.PlaceOrderRequestDto;
import mate.academy.onlinebookstore.dto.order.item.OrderItemResponseDto;
import mate.academy.onlinebookstore.dto.order.item.UpdateRequestOrderItemDto;
import mate.academy.onlinebookstore.model.OrderItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Component
@Mapper(config = MapperConfig.class)
public interface OrderItemMapper {
    OrderItem toEntity(PlaceOrderRequestDto requestDto);

    OrderItem toEntity(UpdateRequestOrderItemDto requestDto);

    @Mapping(target = "bookId", source = "book.id")
    OrderItemResponseDto toDto(OrderItem orderItem);
}
