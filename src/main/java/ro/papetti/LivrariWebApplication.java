package ro.papetti;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class LivrariWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(LivrariWebApplication.class, args);
	}

}
