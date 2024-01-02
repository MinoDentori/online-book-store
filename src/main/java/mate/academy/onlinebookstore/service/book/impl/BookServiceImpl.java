package mate.academy.onlinebookstore.service.book.impl;

import static mate.academy.onlinebookstore.util.ErrorMessagesConstants.BOOK_NOT_FOUND_WITH_ID;

import jakarta.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import mate.academy.onlinebookstore.dto.book.BookDto;
import mate.academy.onlinebookstore.dto.book.BookDtoWithoutCategoryIds;
import mate.academy.onlinebookstore.dto.book.BookSearchParametersDto;
import mate.academy.onlinebookstore.dto.book.CreateBookRequestDto;
import mate.academy.onlinebookstore.exception.EntityNotFoundException;
import mate.academy.onlinebookstore.mapper.BookMapper;
import mate.academy.onlinebookstore.model.Book;
import mate.academy.onlinebookstore.model.Category;
import mate.academy.onlinebookstore.repository.book.BookRepository;
import mate.academy.onlinebookstore.repository.book.BookSpecificationBuilder;
import mate.academy.onlinebookstore.repository.category.CategoryRepository;
import mate.academy.onlinebookstore.service.book.BookService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class BookServiceImpl implements BookService {
    private final CategoryRepository categoryRepository;
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;
    private final BookSpecificationBuilder bookSpecificationBuilder;

    @Override
    public BookDto createBook(CreateBookRequestDto bookDto) {
        Book book = bookMapper.toModel(bookDto);
        Set<Long> categoryIds = bookDto.getCategoryIds();
        setCategoriesToBook(book, categoryIds);
        Book savedBook = bookRepository.save(book);
        return bookMapper.toDto(savedBook);
    }

    @Override
    public List<BookDto> getAll(Pageable pageable) {
        return bookRepository.findAll(pageable).stream()
                .map(bookMapper::toDto)
                .toList();
    }

    @Override
    public List<BookDto> search(BookSearchParametersDto searchParametersDto) {
        Specification<Book> bookSpecification = bookSpecificationBuilder.build(searchParametersDto);
        return bookRepository.findAll(bookSpecification)
                .stream()
                .map(bookMapper::toDto)
                .toList();
    }

    @Override
    public List<BookDtoWithoutCategoryIds> findAllBookByCategory(Long id) {
        return bookRepository.findAllByCategoryId(id);
    }

    @Override
    public BookDto findById(Long id) {
        Book foundedBook = bookRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException(BOOK_NOT_FOUND_WITH_ID + id));
        return bookMapper.toDto(foundedBook);
    }

    @Override
    @Transactional
    public BookDto updateBookById(Long id, CreateBookRequestDto requestDto) {
        bookRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException(BOOK_NOT_FOUND_WITH_ID + id));
        Book updatedBook = bookMapper.toModel(requestDto);
        updatedBook.setId(id);
        Set<Long> categoryIds = requestDto.getCategoryIds();
        setCategoriesToBook(updatedBook, categoryIds);
        bookRepository.save(updatedBook);
        return bookMapper.toDto(updatedBook);
    }

    @Override
    public void deleteBookById(Long id) {
        bookRepository.deleteById(id);
    }

    private void setCategoriesToBook(Book book, Set<Long> categoryIds) {
        Set<Category> categories = new HashSet<>(
                categoryRepository.findAllById(categoryIds));

        book.setCategories(categories);
    }
}
