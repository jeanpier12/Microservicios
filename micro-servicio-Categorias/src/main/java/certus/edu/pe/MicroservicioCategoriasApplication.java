package certus.edu.pe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
@EnableEurekaClient
@SpringBootApplication
public class MicroservicioCategoriasApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroservicioCategoriasApplication.class, args);
	}

}
