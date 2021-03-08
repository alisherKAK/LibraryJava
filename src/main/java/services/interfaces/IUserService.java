package services.interfaces;

import entities.Book;
import entities.User;

import java.util.List;

public interface IUserService {
    List<User> getAll();

    List<Book> getReadBooks(User user);

    boolean readBook(User user, int bookId);

    boolean create(User user);

    User get(int id);

    boolean delete(int id);
}
