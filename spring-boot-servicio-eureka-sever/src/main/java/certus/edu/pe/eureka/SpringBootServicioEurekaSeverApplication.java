package certus.edu.pe.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

//anotacion para trabajar con eureka server
@EnableEurekaServer
@SpringBootApplication
public class SpringBootServicioEurekaSeverApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootServicioEurekaSeverApplication.class, args);
	}

}
