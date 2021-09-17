package by.itransition.itrwebapp.controllers;

import by.itransition.itrwebapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.text.SimpleDateFormat;

@Controller
public class UserPageController {
    private final UserService userService;

    @Autowired
    public UserPageController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user")
    public String addUser(Model model, Principal principal){
        OAuth2AuthenticationToken token = (OAuth2AuthenticationToken) principal;
        if (userService.isContainsByToken(token)){
            userService.setLastDateByUsername(token);
        }else{
            userService.addUserByToken(token);
        }
        model.addAttribute("users",userService.findAllByOrderByIdAsc());
        model.addAttribute("dateformat",new SimpleDateFormat("yyyy.MM.dd"));
        return "userpage";
    }

}
