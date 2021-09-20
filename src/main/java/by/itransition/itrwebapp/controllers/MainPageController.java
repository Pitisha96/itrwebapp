package by.itransition.itrwebapp.controllers;

import by.itransition.itrwebapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class MainPageController {
    private final UserService userService;

    @Autowired
    public MainPageController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String greeting(Model model,Principal principal){
        if(principal!=null){
            OAuth2AuthenticationToken token = (OAuth2AuthenticationToken)principal;
            model.addAttribute("blocked",userService.findAllByToken(token).get(0).isBlocked());
        }
        model.addAttribute("countAllUsers",userService.countUsers());
        model.addAttribute("countGoogle",userService.countAllBySocial("google"));
        model.addAttribute("countGithub",userService.countAllBySocial("github"));
        model.addAttribute("countFacebook",userService.countAllBySocial("facebook"));
        return "mainpage";
    }
}
