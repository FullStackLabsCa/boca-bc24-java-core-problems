package io.reactivestax;

import io.reactivestax.config.AppConfig;
import io.reactivestax.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);
        userService.performTimeOperation();

        userService.getUser("Dhruv");

        userService.clearUserCache("Dhruv");

        userService.updateUser("Dhruv");

        userService.performSynchronizedTask();
    }
}