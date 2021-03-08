package entities;

public class UserBook {
    private int id;
    private int userId;
    private int bookId;

    public UserBook(int id, int userId, int bookId) {
        this.userId = userId;
        this.bookId = bookId;
    }

    public UserBook(int userId, int bookId) {
        this.userId = userId;
        this.bookId = bookId;
    }

    public UserBook() {
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "UserBook{" +
                "id=" + id +
                ", userId=" + userId +
                ", bookId=" + bookId +
                '}';
    }
}
