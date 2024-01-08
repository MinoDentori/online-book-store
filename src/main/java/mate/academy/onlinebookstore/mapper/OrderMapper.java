package mate.academy.onlinebookstore.mapper;

import java.util.List;
import mate.academy.onlinebookstore.config.MapperConfig;
import mate.academy.onlinebookstore.dto.order.OrderDto;
import mate.academy.onlinebookstore.model.Order;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class, uses = OrderItemMapper.class)
public interface OrderMapper {
    @Mapping(target = "userId", source = "user.id")
    OrderDto toDto(Order order);

    @IterableMapping(elementTargetType = OrderDto.class)
    List<OrderDto> listToDto(List<Order> orderList);
}
