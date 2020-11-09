package se.domain;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class User {


    // таблица ролей связана с таблицей пользователей
    // роль - логин
    private Integer id = 0;


    @NotBlank
    @Size(min=3,message = "min 3 characters")
    private String login;

    @NotBlank
    @Size(min=3,message = "min 3 characters")
    private String password;
    private String role;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
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

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
