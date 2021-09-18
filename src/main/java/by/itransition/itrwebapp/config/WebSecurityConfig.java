package by.itransition.itrwebapp.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                    .antMatchers("/","/error","/resources/**").permitAll()
                    .antMatchers("/css/**").permitAll()
                    .antMatchers("/img/**").permitAll()
                    .antMatchers("/js/**").permitAll()
                    .anyRequest().authenticated()
                    .and()
                .oauth2Login()
                    .loginPage("/")
                    .defaultSuccessUrl("/user")
                    .failureUrl("/")
                    .and()
                .formLogin()
                .and()
                .logout();
    }
}
