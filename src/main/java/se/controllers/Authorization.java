package se.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import se.domain.User;
import se.repository.UserRepository;
import se.repository.UserRepositoryImpl;
import se.service.FileService;
import se.service.FileServiceImpl;

import javax.validation.Valid;

@Controller
public class Authorization {
    static FileService fileRepository = new FileServiceImpl();
    static UserRepository users = new UserRepositoryImpl();

    @GetMapping()
    public String index(Model model) {

        model.addAttribute("user", new User());
        return "login";
    }

    @PostMapping("/login")
    public String signIn(@Valid User user, Errors errors, Model model) {
        if(errors.hasErrors()){
            return "login";
        }

        if (users.getByLogPass(user.getLogin().trim(), user.getPassword().trim()) == null) {
            return "registration";
        } else {
            int id = users.getId(users.getByLogPass(user.getLogin(), user.getPassword()));// id - user

            if (users.getByLogPass(user.getLogin(), user.getPassword()).getRole().equals("admin")) {

                model.addAttribute("files", fileRepository.getAll());
                model.addAttribute("users", users.getAll());
                model.addAttribute("user_id", id);
                return "admin/order";
            } else {
                model.addAttribute("user", new User());
                model.addAttribute("user_id", id);
                model.addAttribute("files", fileRepository.getAllById(id));
                return "user/order";
            }
        }
    }


    @GetMapping("/registration")
    public String registration(Model model) {

        model.addAttribute("user", new User());
        return "registration";
    }


    @PostMapping("/registration")
    public String addUser(@Valid User user, Errors errors,
                          Model model) {
        if(errors.hasErrors()){
            return "registration";
        }

        User userFromDB = users.getByLogPass(user.getLogin(), user.getPassword());
        if (userFromDB == null) {
            userFromDB = new User();
            userFromDB.setLogin(user.getLogin());
            userFromDB.setPassword(user.getPassword());
            userFromDB.setRole("user");
            users.save(userFromDB);
        }

        model.addAttribute("files", fileRepository.getAllById(users.getId(userFromDB)));
        model.addAttribute("user_id", users.getId(userFromDB));
        return "user/order";
    }





}
