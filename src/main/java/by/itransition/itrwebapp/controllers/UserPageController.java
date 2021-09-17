package by.itransition.itrwebapp.controllers;

import by.itransition.itrwebapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class UserPageController {
    private final UserService userService;

    @Autowired
    public UserPageController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user")
    public String adduser(Principal principal){
        OAuth2AuthenticationToken token = (OAuth2AuthenticationToken) principal;
        if (userService.isContainsByToken(token))
            userService.setLastDateByUsername(token);
        else
            userService.addUserByToken(token);
        //facebook id
        //google email
        //github login
        return "userpage";
    }

}
