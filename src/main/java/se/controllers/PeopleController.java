package se.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import se.domain.File;
import se.service.FileService;
import se.service.FileServiceImpl;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PeopleController {

    FileService fileRepository = new FileServiceImpl();

    @GetMapping()
    public String index(Model model) {

        model.addAttribute("files",fileRepository.getAll());
        return "registration";
    }


}
