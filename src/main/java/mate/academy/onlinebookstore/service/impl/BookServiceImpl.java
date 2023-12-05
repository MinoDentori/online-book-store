package mate.academy.onlinebookstore.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import mate.academy.onlinebookstore.dto.BookDto;
import mate.academy.onlinebookstore.dto.CreateBookRequestDto;
import mate.academy.onlinebookstore.exception.EntityNotFoundException;
import mate.academy.onlinebookstore.mapper.BookMapper;
import mate.academy.onlinebookstore.model.Book;
import mate.academy.onlinebookstore.repository.BookRepository;
import mate.academy.onlinebookstore.service.BookService;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    @Override
    public BookDto createBook(CreateBookRequestDto bookDto) {
        Book book = bookMapper.toModel(bookDto);
        Book savedBook = bookRepository.createBook(book);
        return bookMapper.toDto(savedBook);
    }

    @Override
    public List<BookDto> getAll() {
        return bookRepository.getAll().stream()
                .map(bookMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public BookDto findById(Long id) {
        Book foundedBook = bookRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Can't get employee from DB by id:" + id));
        return bookMapper.toDto(foundedBook);
    }

}
