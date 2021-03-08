package services;

import entities.Book;
import entities.User;
import entities.UserBook;
import repositories.interfaces.IBookRepository;
import repositories.interfaces.IUserBookRepository;
import repositories.interfaces.IUserRepository;
import services.interfaces.IUserService;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class UserService implements IUserService {
    @Inject
    private IUserRepository userRepository;

    @Inject
    private IUserBookRepository userBookRepository;

    @Inject
    private IBookRepository bookRepository;

    @Override
    public List<User> getAll() {
        return userRepository.getAll();
    }

    @Override
    public List<Book> getReadBooks(User user){
        List<UserBook> userBooks = userBookRepository.getUserBooksByUserId(user.getId());
        List<Book> books = new ArrayList<>();

        for(UserBook userBook : userBooks){
            Book book = bookRepository.get(userBook.getBookId());
            books.add(book);
        }

        return books;
    }

    public boolean readBook(User user, int bookId){
        Book book = bookRepository.get(bookId);

        if(book != null){
            UserBook userBook = new UserBook(user.getId(), bookId);
            userBookRepository.create(userBook);

            return true;
        }

        return false;
    }

    @Override
    public boolean create(User user) {
        return userRepository.create(user);
    }

    @Override
    public User get(int id) {
        return userRepository.get(id);
    }

    @Override
    public boolean delete(int id) {
        return userRepository.delete(id);
    }
}
