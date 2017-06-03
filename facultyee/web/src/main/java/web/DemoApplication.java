package web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({"web", "by.goncharov.nc.abstracts", "by.goncharov.nc.constants", "by.goncharov.nc.service.impl", "by.goncharov.nc.dao.impl", "by.goncharov.nc.exceptions", "by.goncharov.nc.utils", "by.goncharov.nc.config"})
@EnableAutoConfiguration
public class DemoApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication springApplication = new SpringApplication(DemoApplication.class);
		ApplicationContext context = springApplication.run(args);
		System.out.println(context.getApplicationName());
	}
}
