package mate.academy.onlinebookstore.service.impl;

import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.onlinebookstore.dto.BookDto;
import mate.academy.onlinebookstore.dto.CreateBookRequestDto;
import mate.academy.onlinebookstore.mapper.BookMapper;
import mate.academy.onlinebookstore.repository.BookRepository;
import mate.academy.onlinebookstore.service.BookService;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    @Override
    public BookDto save(CreateBookRequestDto bookDto) {
        return null;
    }

    @Override
    public List<BookDto> findAll() {
        return null;
    }

    @Override
    public BookDto getBookById(Long id) {
        return null;
    }
}
