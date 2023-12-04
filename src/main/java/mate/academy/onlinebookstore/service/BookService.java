package mate.academy.onlinebookstore.service;

import java.util.List;
import mate.academy.onlinebookstore.dto.BookDto;
import mate.academy.onlinebookstore.dto.CreateBookRequestDto;

public interface BookService {
    BookDto createBook(CreateBookRequestDto bookDto);

    List<BookDto> getAll();

    BookDto getById(Long id);
}
