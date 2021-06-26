package search.api;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan({"search.api"})
@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class ApiApplication {
    public static ConfigurableApplicationContext context;

    public static void main(String[] args) {
        context = SpringApplication.run(ApiApplication.class, args);
    }

}

