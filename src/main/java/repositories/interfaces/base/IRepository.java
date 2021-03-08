package repositories.interfaces.base;

import java.util.List;

public interface IRepository<T> {
    boolean create(T user);

    T get(int id);

    List<T> getAll();

    boolean delete(int id);
}
