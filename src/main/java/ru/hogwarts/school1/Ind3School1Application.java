package ru.hogwarts.school1;

import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.logging.Logger;

@SpringBootApplication
public class Ind3School1Application {
    private final static Logger logger = (Logger) LoggerFactory.getLogger(Ind3School1Application.class);

    public static void main(String[] args) {
        logger.info("Application Ind3School1Application is starting! ...");
        SpringApplication.run(Ind3School1Application.class, args);
    }

    }