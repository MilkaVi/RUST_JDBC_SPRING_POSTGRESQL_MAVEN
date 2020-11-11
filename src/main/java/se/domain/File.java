
package se.domain;

import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.*;

public class File {
    private Integer id;


    @Digits(integer=3, fraction=0, message = "Не более 3-х знаков")
    @NotNull
    private Integer user_id;

    @NotBlank
    @Size(min=1,message = "min 1 characters")
    private String name;

    @NotBlank
    @Size(min=1,message = "min 1 characters")
    private String date;
    private Integer file_user;

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getFile_user() {
        return file_user;
    }

    public void setFile_user(Integer file_user) {
        this.file_user = file_user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "File{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", date='" + date + '\'' +
                ", file_user=" + file_user +
                '}';
    }
}
