package com.example.rabbitmq_project;

import com.example.rabbitmq_project.config.RabbitConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(RabbitConfiguration.class)
public class RabbitmqProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(RabbitmqProjectApplication.class, args);
	}

}
