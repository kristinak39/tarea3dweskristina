package com.kristina.tarea3dweskristina;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.kristina.tarea3dweskristina")
public class Tarea3dweskristinaApplication {
	
	@Bean
	public Principal applicationStartupRunner() {
		return new Principal();
	}

	public static void main(String[] args) {
		SpringApplication.run(Tarea3dweskristinaApplication.class, args);
	}

}
