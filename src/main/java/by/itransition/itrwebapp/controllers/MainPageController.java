package by.itransition.itrwebapp.controllers;

import by.itransition.itrwebapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.security.Principal;

@Controller
public class MainPageController {
    private final UserService userService;

    @Autowired
    public MainPageController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String greeting(Model model){
        model.addAttribute("countAllUsers",userService.countUsers());
        model.addAttribute("countGoogle",userService.countAllBySocial("google"));
        model.addAttribute("countGithub",userService.countAllBySocial("github"));
        model.addAttribute("countFacebook",userService.countAllBySocial("facebook"));
        return "mainpage";
    }
}
