package com.example.demo;

import com.example.demo.domain.Course;
import com.example.demo.domain.User;

import com.example.demo.repository.CourseRepossitory;
import com.example.demo.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Slf4j
public class DataInitializer implements CommandLineRunner {


    @Autowired
    UserRepository users;
    
	@Autowired
    CourseRepossitory repository;


    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        
        log.info("Preloading" + repository.save(new Course("Introduction to Prolog", "FH",  "Raghu", 299., "2020-01-01", "2020-06-01")));
		log.info("Preloading" + repository.save(new Course("Introduction to MachneLearning", "Universität","Raghu", 399., "2020-01-01", "2020-06-01")));
		
		for(Course course : repository.findAll()) {
			log.info(course.toString());
		}

        this.users.save(User.builder()
            .username("user")
            .password(this.passwordEncoder.encode("password"))
            .roles(Arrays.asList( "ROLE_USER"))
            .build()
        );

        this.users.save(User.builder()
            .username("admin")
            .password(this.passwordEncoder.encode("password"))
            .roles(Arrays.asList("ROLE_USER", "ROLE_ADMIN"))
            .build()
        );

        log.debug("printing all users...");
        this.users.findAll().forEach(v -> log.debug(" User :" + v.toString()));
    }
}
