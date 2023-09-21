package com.bosonit.block5profiles;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ProfileRunner implements CommandLineRunner {


    @Value("${spring.profiles.active}")
    private String activeProfile;

    @Value("${bd.url}")
    private String dbUrl;

    @Override
    public void run(String... args) {
        System.out.println("Active Profile: " + activeProfile);
        System.out.println("DB URL: " + dbUrl);
    }
}
