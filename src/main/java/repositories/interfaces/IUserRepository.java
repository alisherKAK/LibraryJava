package repositories.interfaces;

import entities.User;
import repositories.interfaces.base.IRepository;

public interface IUserRepository extends IRepository<User> {
    public User getUserByLogin(String username, String password);
    public User getUserByUsername(String issuer);
}
