package mate.academy.onlinebookstore.mapper;

import mate.academy.onlinebookstore.config.MapperConfig;
import mate.academy.onlinebookstore.dto.cart.item.CartItemDto;
import mate.academy.onlinebookstore.model.CartItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class)
public interface CartItemMapper {
    @Mapping(target = "bookId", source = "book.id")
    @Mapping(target = "bookTitle", source = "book.title")
    CartItemDto toDto(CartItem cartItem);
}
