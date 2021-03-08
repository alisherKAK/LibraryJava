import db.PostgresDB;
import db.interfaces.IDB;
import org.glassfish.jersey.internal.inject.AbstractBinder;
import repositories.BookRepository;
import repositories.UserBookRepository;
import repositories.UserRepository;
import repositories.interfaces.IBookRepository;
import repositories.interfaces.IUserRepository;
import services.AuthService;
import services.BookService;
import services.UserService;
import services.interfaces.IAuthService;
import services.interfaces.IBookService;
import services.interfaces.IUserService;

public class MyApplicationBinder extends AbstractBinder {
    @Override
    protected void configure() {
        bind(PostgresDB.class).to(IDB.class);

        bind(UserRepository.class).to(IUserRepository.class);
        bind(BookRepository.class).to(IBookRepository.class);
        bind(UserBookRepository.class).to(IUserRepository.class);

        bind(BookService.class).to(IBookService.class);
        bind(UserService.class).to(IUserService.class);
        bind(AuthService.class).to(IAuthService.class);
    }
}