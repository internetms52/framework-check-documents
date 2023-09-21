package framework_integration_check.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;

@org.springframework.boot.autoconfigure.SpringBootApplication
public class SpringBootApplication {
	private static Logger logger = LoggerFactory.getLogger(SpringBootApplication.class);
	public static void main(String[] args) {
		logger.info("spring-jpa2712-hibernate617-final");
		SpringApplication.run(SpringBootApplication.class, args);
	}

}
