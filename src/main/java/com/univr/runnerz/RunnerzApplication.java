package com.univr.runnerz;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RunnerzApplication {

    private  static final Logger log = LoggerFactory.getLogger(RunnerzApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(RunnerzApplication.class, args);

    }

    //CommandLineRunner is an interface that is a interface and we don-t need to create a class were it override the run function
    //we can use a lambda function
    //we can use it to insert some data in the application
//    @Bean
//    CommandLineRunner runner(RunRepository runRepository){
//        return args -> {
//            Run run = new Run(1, "first Run", LocalDateTime.now(), LocalDateTime.now().plus(1, ChronoUnit.HOURS), 5, Location.OUTDOOR);
//            runRepository.create(run);
//        };
//    }

}
