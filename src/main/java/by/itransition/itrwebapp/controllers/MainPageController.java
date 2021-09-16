package by.itransition.itrwebapp.controllers;

import by.itransition.itrwebapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainPageController {
    private final UserService userService;

    @Autowired
    public MainPageController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String greeting(){

        return "mainpage";
    }
}
