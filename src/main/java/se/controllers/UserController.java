package se.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import se.domain.File;
import se.domain.User;
import se.repository.UserRepository;
import se.repository.UserRepositoryImpl;
import se.service.FileService;
import se.service.FileServiceImpl;


@Controller
public class UserController {
    static FileService fileRepository = new FileServiceImpl();
    static UserRepository users = new UserRepositoryImpl();

    @GetMapping("/user/order")
    public String getOrderPage(Model model, @RequestParam(value = "user_id") int id) {
        model.addAttribute("user_id", id);
        model.addAttribute("files", fileRepository.getAllById(id));
        return "user/order";
    }

    @GetMapping("/user/add-new-order/{id}")
    public String addNewOrderPage(@PathVariable("id") Integer user_id, Model model) {//@PathVariable("id") Integer id,
                model.addAttribute("user_id", user_id);
        return "user/addNewOrder";
    }

    @PostMapping("/user/add-new-order/{id}")
    public String addNewOrder(@RequestParam(value = "new_file_id") int new_file_id,
                              @RequestParam(value = "name") String name,
                              @RequestParam(value = "date") String date,
                              @RequestParam(value = "user_id") int user_id, Model model) {
        File file = new File();
        file.setUser_id(new_file_id);
        file.setName(name);
        file.setDate(date);
        file.setFile_user(user_id);

        fileRepository.save(file);

        model.addAttribute("files", fileRepository.getAllById(user_id));
        model.addAttribute("user_id", user_id);

        return "user/order"; // команда, которая сделает перенаправление на другой урл
    }


    @GetMapping("/user/update/{id}")
    public String updateItemPage(@PathVariable("id") Integer id, //@RequestParam("user_id") int user_id,
                                 Model model) {
        // id - file
        model.addAttribute("file_id", id);
        return "user/update";
    }


    @PostMapping("/user/update/{id}")
    public String updateItem(@RequestParam("file_id") Integer id,
                             @RequestParam("new_file_id") Integer new_file_id,
                             @RequestParam(value = "name") String name,
                             @RequestParam(value = "date") String date, Model model) {
        // id - file
        fileRepository.update(id, new_file_id, name, date);
        model.addAttribute("files", fileRepository.getAllById(fileRepository.getById(id).getFile_user()));
        model.addAttribute("user_id", fileRepository.getById(id).getFile_user());
        return "user/order";
    }


    @GetMapping("/user/delete/{id}")
    public String deleteItem(@PathVariable Integer id, Model model) {
        //id - file
        int oldId = fileRepository.getById(id).getFile_user();

        fileRepository.delete(id);

        model.addAttribute("files", fileRepository.getAllById(oldId));
        model.addAttribute("user_id", oldId);

        return "user/order";
    }


    @GetMapping("/user/select")
    public String getOrderFilter(@RequestParam(value = "file_id", required = false, defaultValue = "0") Integer file_id,
                                 @RequestParam(value = "user_id", required = false) Integer user_id,
                                 @RequestParam(value = "name", required = false) String name,
                                 @RequestParam(value = "date", required = false) String date, Model model) {

        // id - file

        model.addAttribute("files", fileRepository.select(user_id, file_id, name, date));
        model.addAttribute("user_id", user_id);
        return "user/order";
    }



}
