package com.verint.todoapi;

import lombok.Generated;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Generated
@SpringBootApplication
public class ToDoApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ToDoApiApplication.class, args);
    }

}
