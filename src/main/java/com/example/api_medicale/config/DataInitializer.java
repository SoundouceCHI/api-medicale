package com.example.api_medicale.config;

import com.example.api_medicale.entities.User;
import com.example.api_medicale.repositories.IUserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initUser(IUserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            if (userRepository.findByUsername("test1").isEmpty()) {
                User user = new User();
                user.setUsername("test1");
                userRepository.save(user);
            }
        };
    }
}
