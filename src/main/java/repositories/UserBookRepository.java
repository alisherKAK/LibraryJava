package repositories;

import entities.Book;
import entities.User;
import entities.UserBook;
import repositories.interfaces.AbstractRepository;
import repositories.interfaces.IUserBookRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserBookRepository extends AbstractRepository<UserBook> implements IUserBookRepository {

    public UserBookRepository() throws InstantiationException, IllegalAccessException {
        fillFields();
    }

    public List<UserBook> getUserBooksByUserId(int userId){
        Connection con = null;
        try {
            con = db.getConnection();
            String sql = "SELECT id, userId, bookId FROM user_book WHERE userId=?";
            PreparedStatement st = con.prepareStatement(sql);

            st.setInt(1,userId);

            ResultSet rs = st.executeQuery();
            List<UserBook> userBooks = new ArrayList<>();
            while(rs.next()) {
                UserBook userBook = new UserBook(rs.getInt("id"),
                        rs.getInt("userId"),
                        rs.getInt("bookId")
                );

                userBooks.add(userBook);

            }

            return userBooks;
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

    public List<UserBook> getUserBooksByBookId(int bookId){
        Connection con = null;
        try {
            con = db.getConnection();
            String sql = "SELECT id, userId, bookId FROM user_book WHERE bookId=?";
            PreparedStatement st = con.prepareStatement(sql);

            st.setInt(1, bookId);

            ResultSet rs = st.executeQuery();
            List<UserBook> userBooks = new ArrayList<>();
            while(rs.next()) {
                UserBook userBook = new UserBook(rs.getInt("id"),
                        rs.getInt("userId"),
                        rs.getInt("bookId")
                );

                userBooks.add(userBook);

            }

            return userBooks;
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
