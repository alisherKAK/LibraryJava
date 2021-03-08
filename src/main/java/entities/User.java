package entities;

public class User {
    private int id;
    private String name;
    private String surname;
    private boolean gender;
    private String username;
    private String password;

    public User() {

    }

    public User(String name, String surname, boolean gender, String username, String password) {
        setName(name);
        setSurname(surname);
        setGender(gender);
        setUsername(username);
        setPassword(password);
    }

    public User(int id, String name, String surname, boolean gender, String username, String password) {
        this(name, surname, gender, username, password);
        setId(id);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public boolean getGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", gender=" + (gender ? "Male" : "Female") +
                '}';
    }
}
