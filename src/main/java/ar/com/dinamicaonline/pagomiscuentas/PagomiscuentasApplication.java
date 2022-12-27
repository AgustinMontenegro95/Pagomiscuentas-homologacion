package ar.com.dinamicaonline.pagomiscuentas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class PagomiscuentasApplication {

	public static void main(String[] args) {
		SpringApplication.run(PagomiscuentasApplication.class, args);
	}

}
