package db.interfaces;

import java.sql.Connection;

public interface IDB {
    Connection getConnection();
}
