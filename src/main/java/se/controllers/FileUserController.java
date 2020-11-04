package se.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import se.domain.File;
import se.domain.User;
import se.repository.UserRepository;
import se.repository.UserRepositoryImpl;
import se.service.FileService;
import se.service.FileServiceImpl;
import sun.security.util.Password;

import java.util.ArrayList;
import java.util.List;

@Controller
public class FileUserController {

    FileService fileRepository = new FileServiceImpl();
    UserRepository users = new UserRepositoryImpl();


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

            int id = users.getId(users.getByLogPass(login, password));

            if (users.getByLogPass(login, password).getRole().equals("admin")) {
                //for admin

                model.addAttribute("files", fileRepository.getAll());
                model.addAttribute("id", id);
                return "order";
            } else {
                // for users

                model.addAttribute("files", fileRepository.getAllById(id));
                model.addAttribute("id", id);
                return "order";
            }

            //проверка админ или юзер
            //когда залогинился юзер создавать новую таблицу или все файлы будут в одной?
            //если в одной то как быть с одинаковыми полями(id)
            //где хранить индексы файлов
            //лучше создавать  для каждого пользоваетлся новую таблицу
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
        return "order";
    }

    // ===== ????????????
    @GetMapping("/order")
    public String getOrderPage(Model model) {
        model.addAttribute("files", fileRepository.getAll());
        return "order";
    }


    @GetMapping("/add-new-order/{id}")
    public String addNewOrderPage(@PathVariable Integer id, Model model) {
        model.addAttribute("id", id);
        return "addNewOrder";
    }

    @PostMapping("/add-new-order/{id}")
    public String addNewOrder(@RequestParam(value = "id") int id, @RequestParam(value = "title") String title,
                              @RequestParam(value = "price") String date, @RequestParam(value = "file_user") int file_user, Model model) {
        File order = new File();
        order.setUser_id(id);
        order.setName(title);
        order.setDate(date);
        order.setFile_user(file_user);
        System.out.println(order.getFile_user() + " id_user=" + file_user);
        fileRepository.save(order);
        if (users.getByFileUser(file_user).getRole().equals("admin")) {
            model.addAttribute("files", fileRepository.getAll());
        } else {

            model.addAttribute("files", fileRepository.getAllById(file_user));
            model.addAttribute("id", file_user);
        }
        return "order"; // команда, которая сделает перенаправление на другой урл
    }

    @GetMapping("/update/{id}")
    public String updateItemPage(@PathVariable Integer id, Model model) {
        System.out.println(id);
        model.addAttribute("id", id);
        return "update";
    }

    @PostMapping("/update/{id}")
    public String updateItem(@RequestParam("id") int id, @RequestParam("user_id") int user_id,
                             @RequestParam(value = "title") String title,
                             @RequestParam(value = "price") String date, Model model) {
        // id
        // admin
        fileRepository.update(user_id, id, title, date);
        model.addAttribute("files", fileRepository.getAllById(fileRepository.getById(id).getFile_user()));
        return "order"; // команда, которая сделает перенаправление на другой урл
    }

    @GetMapping("/delete/{id}")
    public String deleteItem(@PathVariable Integer id, Model model) {
        //File order = fileRepository.getById(id);
        fileRepository.delete(id);
        model.addAttribute("files", fileRepository.getAll());
        return "order";
    }

    @GetMapping("/select/{id}")
    public String getOrderFilter(@RequestParam(value = "id", required = false) String id,
                                 @RequestParam(value = "user_id", required = false, defaultValue = "0") String user_id,
                                 @RequestParam(value = "title", required = false) String title,
                                 @RequestParam(value = "date", required = false) String date, Model model) {

        if (fileRepository.getById(Integer.valueOf(id)).getFile_user().equals("admin")) {
            // select empty???
            model.addAttribute("files", fileRepository.select(Integer.valueOf(id), Integer.valueOf(user_id), title, date));
            model.addAttribute("id", Integer.valueOf(id));
        } else {
            model.addAttribute("files", fileRepository.select(Integer.valueOf(id), Integer.valueOf(user_id), title, date));
            model.addAttribute("id", Integer.valueOf(id));
        }
        return "order";
    }


}
