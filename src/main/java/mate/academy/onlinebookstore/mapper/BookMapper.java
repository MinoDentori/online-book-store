package mate.academy.onlinebookstore.mapper;

import mate.academy.onlinebookstore.dto.BookDto;
import mate.academy.onlinebookstore.model.Book;

public interface BookMapper {
    BookDto toDto(Book book);
    Book toModel(BookDto bookDto);
}
