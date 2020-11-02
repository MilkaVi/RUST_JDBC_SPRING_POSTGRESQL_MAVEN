package se.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import se.domain.File;
import se.repository.UserRepository;
import se.repository.UserRepositoryImpl;
import se.service.FileService;
import se.service.FileServiceImpl;

import java.util.List;

@Controller
public class HomeController {

    private FileService fileRepository = new FileServiceImpl();
    private UserRepository users = new UserRepositoryImpl();

    @GetMapping("/")
    public String login(){
        //return "login";
        return "hello";
    }

    @PostMapping("/login")
    public String signIn(@RequestParam(value = "login") String login,
                         @RequestParam(value = "password") String password, Model model){

        if(users.getByLogPass(login.trim(),password.trim()) == null){
            return "registration";
        }
        else{
            //проверка админ или юзер
            //когда залогинился юзер создавать новую таблицу или все файлы будут в одной?
            //если в одной то как быть с одинаковыми полями(id)
            //где хранить индексы файлов
            //лучше создавать  для каждого пользоваетлся новую таблицу
            model.addAttribute("orderList", fileRepository.getAll());
            return "order";
        }
    }

    @GetMapping("/order")
    public String getOrderPage(Model model) {
        model.addAttribute("orderList", fileRepository.getAll());
        return "order";
    }
    @GetMapping("/add-new-order")
    public String addNewOrderPage(Model model) {
        //List<File> orders = fileRepository.getAll();
        //model.addAttribute("orderList", orders);
        return "addNewOrder";
    }

    @PostMapping("/add-new-order")
    public String addNewOrder(@RequestParam(value="id") int id, @RequestParam(value="title") String title,
                              @RequestParam(value="price") String date,Model model) {
        File order = new File();
        order.setId(id);
        order.setName(title);
        order.setDate(date);
        fileRepository.save(order);
        List<File> orders = fileRepository.getAll();
        model.addAttribute("orderList", orders);
        return "order"; // команда, которая сделает перенаправление на другой урл
    }

    @GetMapping("/update/{id}")
    public String updateItemPage(@PathVariable Integer id,Model model) {
        model.addAttribute("id", id);
        return "update";
    }

    @PostMapping ("/update/{id}")
    public String updateItem( @RequestParam("id") int id,@RequestParam(value="title") String title,
                             @RequestParam(value="price") String date, Model model) {

        fileRepository.update(id, title,date);
        List<File> orders = fileRepository.getAll();
        model.addAttribute("orderList", orders);
        return  "order"; // команда, которая сделает перенаправление на другой урл
    }

    @GetMapping("/delete/{id}")
    public String deleteItem(@PathVariable Integer id, Model model) {
        //File order = fileRepository.getById(id);
        fileRepository.delete(id);
        List<File> orders = fileRepository.getAll();
        model.addAttribute("orderList", orders);
        return "order";
    }

    @GetMapping("/select")
    public String getOrderFilter(@RequestParam(value = "id", required = false, defaultValue = "0") String id,
                                 @RequestParam(value="title", required = false) String title,
                                 @RequestParam(value="date", required = false) String date, Model model) {

        List<File> orders = fileRepository.select(Integer.valueOf(id),title,date);
        model.addAttribute("orderList", orders);
        return "order";
    }

}