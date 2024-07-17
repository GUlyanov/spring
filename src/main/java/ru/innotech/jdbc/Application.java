package ru.innotech.jdbc;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        new AnnotationConfigApplicationContext(Application.class);
    }
}
