package mate.academy.onlinebookstore.repository;

import java.util.List;
import mate.academy.onlinebookstore.model.Book;

public interface BookRepository {
    Book createBook(Book book);

    List<Book> getAll();

    Book getById(Long id);
}
