package services;

import entities.Book;
import repositories.interfaces.IBookRepository;
import services.interfaces.IBookService;

import javax.inject.Inject;
import java.util.List;

public class BookService implements IBookService {
    @Inject
    private IBookRepository bookRepository;

    @Override
    public List<Book> getAll() {
        return bookRepository.getAll();
    }

    @Override
    public Book getBookById(int bookId) {
        return bookRepository.get(bookId);
    }
}
