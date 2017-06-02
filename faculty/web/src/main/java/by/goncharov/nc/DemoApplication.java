package by.goncharov.nc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by ivan on 01.06.2017.
 */


//"by.goncharov.nc.config","by.goncharov.nc.abstracts","by.goncharov.nc.dao","by.goncharov.nc.constants","by.goncharov.nc.entities",
@Configuration
@ComponentScan({"by.goncharov.nc"})
@EnableAutoConfiguration
public class DemoApplication extends SpringBootServletInitializer {


    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(DemoApplication.class);
        ApplicationContext context = springApplication.run(args);
        System.out.println(context.getApplicationName());
    }


}
