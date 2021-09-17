package by.itransition.itrwebapp.services;

import by.itransition.itrwebapp.models.User;
import by.itransition.itrwebapp.repositories.UserRepository;
import by.itransition.itrwebapp.utils.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final SecurityUtil securityUtil;

    @Autowired
    public UserService(UserRepository userRepository, SecurityUtil securityUtil) {
        this.userRepository = userRepository;
        this.securityUtil = securityUtil;
    }

    public void add(User user){
        userRepository.save(user);
    }

    public void addUserByToken(OAuth2AuthenticationToken token){
        User user = new User();
        user.setSocial(token.getAuthorizedClientRegistrationId());
        user.setUsername((String) token.getPrincipal().getAttributes().get(securityUtil.
                keyBySocial(token.getAuthorizedClientRegistrationId())));
        user.setBlocked(false);
        user.setFirstDate(new Date());
        user.setLastDate(new Date());
        add(user);
    }

    public boolean isContainsByToken(OAuth2AuthenticationToken token){
        return !userRepository.findAllByUsername(
                (String) token.getPrincipal().getAttributes()
                        .get(securityUtil.keyBySocial(token.getAuthorizedClientRegistrationId()))
        ).isEmpty();
    }

    public void setLastDateByUsername(OAuth2AuthenticationToken token){
        userRepository.findAllByUsername(
                (String) token.getPrincipal().getAttributes()
                        .get(securityUtil.keyBySocial(token.getAuthorizedClientRegistrationId()))
        ).forEach(e->{
            e.setLastDate(new Date());
            userRepository.save(e);
        });
    }

    public List<User> findAllByOrderByIdAsc(){
        return userRepository.findAllByOrderByIdAsc();
    }
}
