package se.domain;

public class User {


    // таблица ролей связана с таблицей пользователей
    // роль - логин
    private Integer id = 0;
    private String login;
    private String password;
    private String role;
    private static int countUser = 1;

    public User() {
        this.id = countUser;
        countUser++;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
