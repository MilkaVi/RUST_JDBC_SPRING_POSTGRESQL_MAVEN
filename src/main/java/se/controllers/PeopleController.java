package se.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import se.domain.File;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PeopleController {



    @GetMapping()
    public String index(Model model) {
        File file = new File();
        file.setId(1);
        file.setName("kolay");
        file.setDate("999");

        File file1 = new File();
        file1.setId(2);
        file1.setName("slava");
        file1.setDate("111");

        List<File> files = new ArrayList<>();

        files.add(file);
        files.add(file1);
        model.addAttribute("files", files);
        return "order";
    }


}
