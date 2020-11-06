package se.repository;

import se.domain.User;

import java.util.List;

public interface UserRepository {
    void save(User user);

    void delete(Integer id);

    List<User> getAll();

    User getByLogPass(String login, String password);

    User getByFileUser(Integer id);

    Integer getId(User user);



}
