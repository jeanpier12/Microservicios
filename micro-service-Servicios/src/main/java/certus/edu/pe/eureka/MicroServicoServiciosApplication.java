package certus.edu.pe.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
@EnableDiscoveryClient
@SpringBootApplication
public class MicroServicoServiciosApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroServicoServiciosApplication.class, args);
	}

}
