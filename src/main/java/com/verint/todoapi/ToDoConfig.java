package com.verint.todoapi;

import lombok.Generated;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

//  TODO is this a sensible name?
@Generated
@Configuration(proxyBeanMethods = false)
@EnableJpaRepositories("com.verint.todoapi")
public class ToDoConfig {
}
