package repositories;

import entities.Book;
import repositories.interfaces.AbstractRepository;
import repositories.interfaces.IBookRepository;

public class BookRepository extends AbstractRepository<Book> implements IBookRepository {
    public BookRepository() throws InstantiationException, IllegalAccessException {
        fillFields();
    }
}
