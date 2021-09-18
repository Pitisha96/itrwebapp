package by.itransition.itrwebapp.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvsConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        if(!registry.hasMappingForPattern("/static/**")){
            registry.addResourceHandler("/static/**")
                    .addResourceLocations("/static/");
        }
    }
}
