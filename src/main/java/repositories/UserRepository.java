package repositories;

import entities.User;
import repositories.interfaces.AbstractRepository;
import repositories.interfaces.IUserRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRepository extends AbstractRepository<User> implements IUserRepository {

    public UserRepository() throws InstantiationException, IllegalAccessException {
        fillFields();
    }

    public User getUserByLogin(String username, String password) {
        Connection con = null;
        try {
            con = db.getConnection();
            String sql = "SELECT id,name,surname,gender,username,password FROM users WHERE username=? AND password=?";
            PreparedStatement st = con.prepareStatement(sql);

            st.setString(1,username);
            st.setString(2,password);

            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                User user = new User(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("surname"),
                        rs.getBoolean("gender"),
                        rs.getString("username"),
                        rs.getString("password")
                );

                return user;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return null;
    }

    public User getUserByUsername(String issuer) {
        Connection con = null;
        try {
            con = db.getConnection();
            String sql = "SELECT id,name,surname,gender,username,password FROM users WHERE username=?";
            PreparedStatement st = con.prepareStatement(sql);

            st.setString(1,issuer);

            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                User user = new User(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("surname"),
                        rs.getBoolean("gender"),
                        rs.getString("username"),
                        rs.getString("password")
                );

                return user;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return null;
    }
}
