package appSBdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan // adnotacja pozwala skanowac wszystkie klasy w poszukiwaniu adnotacji i je poszukiwać
public class AppdemoApplication {

	public static void main(String[] args) {

		SpringApplication.run(AppdemoApplication.class, args);
	}
}
