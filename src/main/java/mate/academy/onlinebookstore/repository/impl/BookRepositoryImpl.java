package mate.academy.onlinebookstore.repository.impl;

import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.onlinebookstore.exception.EntityNotFoundException;
import mate.academy.onlinebookstore.model.Book;
import mate.academy.onlinebookstore.repository.BookRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class BookRepositoryImpl implements BookRepository {
    private final SessionFactory sessionFactory;

    @Override
    public Book save(Book book) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.persist(book);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Can't insert Book into DB: " + book, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return book;
    }

    @Override
    public List<Book> findAll() {
        try (Session session = sessionFactory.openSession()) {
            Query<Book> getAllBooksQuery = session.createQuery(
                    "FROM Book", Book.class);
            return getAllBooksQuery.getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Can't get a List of Books from DB:", e);
        }
    }

    @Override
    public Book getById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            Query<Book> getBookByIdQuery = session.createQuery(
                    "FROM Book b WHERE b.id = :id ", Book.class);
            getBookByIdQuery.setParameter("id", id);
            return getBookByIdQuery.uniqueResult();
        } catch (Exception e) {
            throw new EntityNotFoundException("Didn't find book in DB with id:" + id, e);
        }
    }
}
