package be.faros;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
 * Project for demonstrating the async support in spring web-mvc leading up to the reactive support we have since Spring 5.x with webflux
 *
 * Resources
 * 		Servlet 3, Async Support
 * 		https://spring.io/blog/2012/05/07/spring-mvc-3-2-preview-introducing-servlet-3-async-support
 *		http://www.logicbig.com/tutorials/spring-framework/spring-web-mvc/http_streaming/
 */
@SpringBootApplication
public class SpringmvcApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringmvcApplication.class, args);
	}
}
