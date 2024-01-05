package mate.academy.onlinebookstore.mapper;


import mate.academy.onlinebookstore.config.MapperConfig;
import mate.academy.onlinebookstore.dto.order.OrderDto;
import mate.academy.onlinebookstore.model.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Component
@Mapper(config = MapperConfig.class, uses = OrderItemMapper.class)
public interface OrderMapper {
    @Mapping(source = "userId", target = "user.id")
    OrderDto toDto(Order order);
}
