package it.giochisulnostrotavolo.listone;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class SpringBootVuejsApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootVuejsApplication.class, args);
	}
	
	@Value("${app.allowedOrigins}")
	String allowedOrigins;
	
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry
		        	.addMapping("/public/api/**")//impostandolo qui è "globale", non c'è bisogno di utilizzare l'annotation @CrossOrigin sui metodi dei controller
		            .allowedOrigins(allowedOrigins.split(","))
		            //.allowedMethods("PUT", "DELETE")
		            //.allowedHeaders("header1", "header2", "header3")
		            //.exposedHeaders("header1", "header2")
		            .allowCredentials(false)
		            //.maxAge(3600)
		            ;
	
		        // Add more mappings...
			}
		};
	}
	
}
