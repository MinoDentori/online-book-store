package mate.academy.onlinebookstore.repository;

import java.util.List;
import java.util.Optional;
import mate.academy.onlinebookstore.model.Book;

public interface BookRepository {
    Book createBook(Book book);

    List<Book> getAll();

    Optional<Book> findById(Long id);
}
