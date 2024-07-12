package ru.innotech.jdbc;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import java.util.Arrays;

@ComponentScan("ru.innotech.jdbc")
public class Application {
    public static void main(String[] args) {
        ApplicationContext ctx =  new AnnotationConfigApplicationContext(Application.class);
        //Arrays.stream(ctx.getBeanDefinitionNames()).forEach(System.out::println);
    }
}
