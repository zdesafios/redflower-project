package redflower.api.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@ComponentScan("redflower")
@EntityScan("redflower.schema")
@EnableMongoRepositories(basePackages = {"redflower.domain.repository"})
public class RedflowerApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(RedflowerApiApplication.class, args);
	}

}
