package agenda.vacina;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;



@SpringBootApplication
@EnableWebMvc
@ComponentScan(basePackages = {"agenda.*"})
@EnableJpaRepositories(basePackages = {"agenda.vacina.repository"})
public class VacinaApplication extends WebMvcConfigurerAdapter  {

	public static void main(String[] args) {
		SpringApplication.run(VacinaApplication.class, args);
	}
	
	
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
	  if (!registry.hasMappingForPattern("/static/**")) {
	     registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
	  }
	}
	
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/login").setViewName("/login");
		registry.setOrder(Ordered.LOWEST_PRECEDENCE);
	}

}
