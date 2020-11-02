package se.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import se.domain.User;
import se.repository.UserRepository;
import se.repository.UserRepositoryImpl;

@Controller
public class Registration {

    private UserRepository users = new UserRepositoryImpl();

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(@RequestParam(value = "login") String login,
                          @RequestParam(value = "password") String password) {
        User userFromDB = users.getByLog(login.trim());
        if (userFromDB == null) {
            User user = new User();
            user.setLogin(login.trim());
            user.setPassword(password.trim());
            user.setRole("user");
            users.save(user);
        }

        return "order";
    }
}
