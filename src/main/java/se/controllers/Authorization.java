package se.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import se.domain.User;
import se.repository.UserRepository;
import se.repository.UserRepositoryImpl;
import se.service.FileService;
import se.service.FileServiceImpl;

@Controller
public class Authorization {
    static FileService fileRepository = new FileServiceImpl();
    static UserRepository users = new UserRepositoryImpl();

    @GetMapping()
    public String index() {
        return "login";
    }

    @PostMapping("/login")
    public String signIn(Model model, @RequestParam(value = "login") String login,
                         @RequestParam(value = "password") String password) {

        if (users.getByLogPass(login.trim(), password.trim()) == null) {
            return "registration";
        } else {
            int id = users.getId(users.getByLogPass(login, password));// id - user

            if (users.getByLogPass(login, password).getRole().equals("admin")) {

                model.addAttribute("files", fileRepository.getAll());
                model.addAttribute("users", users.getAll());
                model.addAttribute("user_id", id);
                return "admin/order";
            } else {
                model.addAttribute("user_id", id);
                model.addAttribute("files", fileRepository.getAllById(id));
                return "user/order";
            }
        }
    }


    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }


    @PostMapping("/registration")
    public String addUser(@RequestParam(value = "login") String login,
                          @RequestParam(value = "password") String password,
                          Model model) {
        User userFromDB = users.getByLogPass(login.trim(), password.trim());
        if (userFromDB == null) {
            userFromDB = new User();
            userFromDB.setLogin(login.trim());
            userFromDB.setPassword(password.trim());
            userFromDB.setRole("user");
            users.save(userFromDB);
        }

        model.addAttribute("files", fileRepository.getAllById(users.getId(userFromDB)));
        model.addAttribute("id", users.getId(userFromDB));
        return "user/order";
    }





}
