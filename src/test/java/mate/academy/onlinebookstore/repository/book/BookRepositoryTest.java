package mate.academy.onlinebookstore.repository.book;

import java.math.BigDecimal;
import java.util.List;
import mate.academy.onlinebookstore.model.Book;
import mate.academy.onlinebookstore.model.Category;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.testcontainers.shaded.org.apache.commons.lang3.builder.EqualsBuilder;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class BookRepositoryTest {
    private static final long DEFAULT_ID = 1L;
    private static final String DEFAULT_ISBN = "80-902734-1-6";
    private static final String DEFAULT_AUTHOR = "Kazuo Ishiguro";
    private static final String DEFAULT_TITLE = "The Buried Giant";
    private static final String DEFAULT_PRICE = "434.00";
    private static final String DEFAULT_SHORT_DESCRIPTION =
            "The Buried Giant is a fantasy novel";
    @Autowired
    private BookRepository bookRepository;

    @Test
    @DisplayName("""
            Find all books with certain existing Category id
            """)
    @Sql(scripts = {
            "classpath:database/book/insert-into-book.sql",
            "classpath:database/category/insert-into-category.sql",
            "classpath:database/category/insert-into-book-category.sql"
    }, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = {
            "classpath:database/category/delete-book-category.sql",
            "classpath:database/book/delete-book.sql",
            "classpath:database/category/delete-category.sql"
    }, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void findAllByCategoryId_ExistingCategoryWithIdOne_ReturnOneBook() {
        Category fanstasyCategory = new Category();
        fanstasyCategory.setId(1L);
        fanstasyCategory.setName("Fantasy");
        fanstasyCategory.setDescription("Fantasy category");

        Book defaultBook = getDefaultBook();
        List<Book> expected = List.of(defaultBook);
        List<Book> actual = bookRepository
                .findAllByCategoryId(fanstasyCategory.getId());
        Assertions.assertNotNull(actual);
        EqualsBuilder.reflectionEquals(expected,actual);
    }

    private Book getDefaultBook() {
        Book defaultBook = new Book();
        defaultBook.setId(DEFAULT_ID);
        defaultBook.setIsbn(DEFAULT_ISBN);
        defaultBook.setAuthor(DEFAULT_AUTHOR);
        defaultBook.setTitle(DEFAULT_TITLE);
        defaultBook.setPrice(new BigDecimal(DEFAULT_PRICE));
        defaultBook.setDescription(DEFAULT_SHORT_DESCRIPTION);
        return defaultBook;
    }
}
