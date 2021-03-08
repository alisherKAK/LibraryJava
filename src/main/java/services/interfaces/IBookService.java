package services.interfaces;

import entities.Book;

import java.util.List;

public interface IBookService {
    List<Book> getAll();

    Book getBookById(int bookId);
}
