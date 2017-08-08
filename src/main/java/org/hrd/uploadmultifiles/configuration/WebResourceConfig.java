package org.hrd.uploadmultifiles.configuration;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by ratha on 08-Aug-17.
 */
@Configuration
public class WebResourceConfig extends WebMvcConfigurerAdapter{

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("classpath:/static/");
        //registry.addResourceHandler("/webjars/**").addResourceLocations("/resources/");
        registry.addResourceHandler("/resources/upload/**").addResourceLocations("file:/opt/project/upload/");
    }

}
