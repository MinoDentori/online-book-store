package mate.academy.onlinebookstore.service.book.impl;

import java.math.BigDecimal;
import java.util.List;
import mate.academy.onlinebookstore.dto.book.BookDto;
import mate.academy.onlinebookstore.mapper.BookMapper;
import mate.academy.onlinebookstore.model.Book;
import mate.academy.onlinebookstore.repository.book.BookRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.testcontainers.shaded.org.apache.commons.lang3.builder.EqualsBuilder;

@ExtendWith(MockitoExtension.class)
class BookServiceImplTest {
    private static final long DEFAULT_ID = 1L;
    private static final String DEFAULT_ISBN = "9781408711705";
    private static final String DEFAULT_AUTHOR = "Kazuo Ishiguro";
    private static final String DEFAULT_TITLE = "The Buried Giant";
    private static final String DEFAULT_PRICE = "434.00";
    private static final String DEFAULT_DESCRIPTION = """
            The Buried Giant is a fantasy novel 
            by the Nobel Prize-winning British writer Kazuo Ishiguro,
             published in March 2015.
            The novel follows an elderly Briton couple, Axl and Beatrice, living in a fictional 
            post-Arthurian England in which no-one is able to retain long-term memories. 
            After dimly recalling that, years earlier, they might have had a son,
            the couple decide to travel to a neighbouring village to seek him out.""";
    private static final String DEFAULT_COVER_IMAGE_LINK = """
            https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww
            .amazon.co.uk%2FBuried-Giant-Kazuo-Ishiguro%2Fdp%2F0571315070
            &psig=AOvVaw13ml9G4S0lS7NHxTvhDTsK&ust=1704823086061000&source=images
            &cd=vfe&opi=89978449&ved=0CBEQjRxqFwoTCOiRvYavzoMDFQAAAAAdAAAAABAD
            """;
    @Mock
    private BookRepository bookRepository;
    @Mock
    private BookMapper bookMapper;
    @InjectMocks
    private BookServiceImpl bookService;

    @Test
    @DisplayName("""
            Get list of all books that persisted in DB
            """)
    void getAll_BookExist_ReturnsListWithAllBooks() {
        Book book = getDefaultBook();
        List<Book> bookList = List.of(book);

        Pageable pageable = PageRequest.of(0,5);
        Page<Book> page = new PageImpl<>(bookList, pageable, bookList.size());

        BookDto bookDto = getDefaultBookDto();

        Mockito.when(bookRepository.findAll(pageable)).thenReturn(page);
        Mockito.when(bookMapper.toDto(book)).thenReturn(bookDto);

        List<BookDto> actual = bookService.getAll(pageable);
        List<BookDto> expected = List.of(bookDto);

        Assertions.assertNotNull(actual);
        EqualsBuilder.reflectionEquals(expected, actual, "id");
    }

    private Book getDefaultBook() {
        Book defaultBook = new Book();
        defaultBook.setId(DEFAULT_ID);
        defaultBook.setIsbn(DEFAULT_ISBN);
        defaultBook.setAuthor(DEFAULT_AUTHOR);
        defaultBook.setTitle(DEFAULT_TITLE);
        defaultBook.setPrice(new BigDecimal(DEFAULT_PRICE));
        defaultBook.setDescription(DEFAULT_DESCRIPTION);
        defaultBook.setCoverImage(DEFAULT_COVER_IMAGE_LINK);
        return defaultBook;
    }

    private BookDto getDefaultBookDto() {
        BookDto defaultBookDto = new BookDto();
        defaultBookDto.setId(DEFAULT_ID);
        defaultBookDto.setIsbn(DEFAULT_ISBN);
        defaultBookDto.setAuthor(DEFAULT_AUTHOR);
        defaultBookDto.setTitle(DEFAULT_TITLE);
        defaultBookDto.setPrice(new BigDecimal(DEFAULT_PRICE));
        defaultBookDto.setDescription(DEFAULT_DESCRIPTION);
        defaultBookDto.setCoverImage(DEFAULT_COVER_IMAGE_LINK);
        return defaultBookDto;
    }
}
