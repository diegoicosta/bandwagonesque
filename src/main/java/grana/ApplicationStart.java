package grana;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by diegoicosta on 07/05/16.
 */
@Configuration
@EnableAutoConfiguration
@ComponentScan
public class ApplicationStart {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(ApplicationStart.class, args);
    }
}
