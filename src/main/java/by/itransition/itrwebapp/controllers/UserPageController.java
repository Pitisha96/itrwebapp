package by.itransition.itrwebapp.controllers;

import by.itransition.itrwebapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
        model.addAttribute("blocked",
                userService.findAllByToken(token).isEmpty() ||
                        userService.findAllByToken(token).get(0).isBlocked()
                        );
        model.addAttribute("idUser",userService.findAllByToken(token).get(0).getId());
        return "userpage";
    }

    @PostMapping("/user")
    public String blockAndDeleteUsers(@RequestParam String action, @RequestParam int[] ids){
        for(int id:ids){
            if (action.equals("delete")) {
                userService.deleteById(id);
            } else if (action.equals("lock")) {
                userService.lockById(id);
            } else {
                userService.unlockById(id);
            }
        }
        return "userpage";
    }
}
