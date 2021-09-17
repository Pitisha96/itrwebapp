package by.itransition.itrwebapp.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.client.authentication.OAuth2LoginAuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class UserPageController {
    @GetMapping("/user")
    public String adduser(Principal principal){
        OAuth2AuthenticationToken token = (OAuth2AuthenticationToken) principal;
        System.out.println(token.getAuthorizedClientRegistrationId());
        System.out.println(principal);
        //System.out.println(token.getPrincipal().getAttributes().get("login"));
        return "userpage";
    }

}
