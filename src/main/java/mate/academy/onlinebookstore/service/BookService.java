package mate.academy.onlinebookstore.service;

import java.util.List;
import mate.academy.onlinebookstore.dto.BookDto;
import mate.academy.onlinebookstore.dto.BookSearchParametersDto;
import mate.academy.onlinebookstore.dto.CreateBookRequestDto;
import org.springframework.data.domain.Pageable;

public interface BookService {
    BookDto createBook(CreateBookRequestDto bookDto);

    List<BookDto> getAll(Pageable pageable);

    BookDto findById(Long id);

    void deleteBookById(Long id);

    BookDto updateBookById(Long id, CreateBookRequestDto requestDto);

    List<BookDto> search(BookSearchParametersDto searchParametersDto);

}
