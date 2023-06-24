package org.jhuachambe.msvc.servicios3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class MsvcServicios3Application {

	public static void main(String[] args) {
		SpringApplication.run(MsvcServicios3Application.class, args);
	}

}
