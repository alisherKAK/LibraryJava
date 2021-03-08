package entities;

public class Book {
    private int id;
    private String name;
    private String publishedDate;
    private String author;

    public Book(int id, String name, String publishedDate, String author) {
        this.id = id;
        this.name = name;
        this.publishedDate = publishedDate;
        this.author = author;
    }

    public Book() {
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(String publishedDate) {
        this.publishedDate = publishedDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", publishedDate='" + publishedDate + '\'' +
                ", author='" + author + '\'' +
                '}';
    }
}
