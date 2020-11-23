package ru.rtmis.melfor.camel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class RestCamelKafkaSpringApplication {
    public static void main(String[] args) {
        SpringApplication.run(RestCamelKafkaSpringApplication.class, args);
    }
}
