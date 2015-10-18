package kr.rang2ne.triprec;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

@SpringBootApplication
public class Application extends WebMvcAutoConfiguration.WebMvcAutoConfigurationAdapter {
    @Value("${kr.rang2ne.triprec.stored.absolute.path}")
    private String absolutePath;
    @Value("${kr.rang2ne.triprec.stored.web.path}")
    private String webPath;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        super.addResourceHandlers(registry);
        registry.addResourceHandler(webPath+"/**")
                .addResourceLocations("file:"+absolutePath+"/");
    }
}
