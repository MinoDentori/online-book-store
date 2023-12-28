package mate.academy.onlinebookstore.mapper;

import java.util.stream.Collectors;
import mate.academy.onlinebookstore.config.MapperConfig;
import mate.academy.onlinebookstore.dto.book.BookDto;
import mate.academy.onlinebookstore.dto.book.BookDtoWithoutCategoryIds;
import mate.academy.onlinebookstore.dto.book.CreateBookRequestDto;
import mate.academy.onlinebookstore.model.Book;
import mate.academy.onlinebookstore.model.Category;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(config = MapperConfig.class, uses = CategoryMapper.class)
public interface BookMapper {
    @Mapping(target = "categoryIds", ignore = true)
    BookDto toDto(Book book);

    Book toModel(CreateBookRequestDto bookDto);

    BookDtoWithoutCategoryIds toBookDtoWithoutCategories(Book book);

    @AfterMapping
    default void setCategoryIds(@MappingTarget BookDto bookDto, Book book) {
        if (!book.getCategories().isEmpty()) {
            bookDto.setCategoryIds(book.getCategories().stream()
                    .map(Category::getId)
                    .collect(Collectors.toSet()));
        }
    }
}
