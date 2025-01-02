package io.reactivestax.service;

import io.reactivestax.annotation.Cache;
import io.reactivestax.annotation.CacheEvict;
import io.reactivestax.annotation.Synchronized;
import io.reactivestax.annotation.Timer;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Timer
    public void performTimeOperation() throws InterruptedException {
        Thread.sleep(400);
        System.out.println("Inside UserService, performTimeOperation");
    }

    @Cache
    public String getUser(String name) {
        System.out.println("Fetching user: " + name);
        return name;
    }

    @CacheEvict
    public void clearUserCache(String name) {
        System.out.println("Clearing cache for user: " + name);
    }

    @Cache
    @CacheEvict
    public void updateUser(String name) {
        System.out.println("Updating user: " + name);
    }

    @Synchronized
    public void performSynchronizedTask() {
        System.out.println("Performing synchronized task");
    }
}
