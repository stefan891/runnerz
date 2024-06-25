package com.univr.runnerz;

import com.univr.runnerz.user.User;
import com.univr.runnerz.user.UserHttpClient;
import com.univr.runnerz.user.UserRestClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

import java.util.List;

@SpringBootApplication
public class RunnerzApplication {

    private  static final Logger log = LoggerFactory.getLogger(RunnerzApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(RunnerzApplication.class, args);

    }

    @Bean
    UserHttpClient userHttpClient(){
        RestClient restClient = RestClient.create("https://jsonplaceholder.typicode.com");
        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(RestClientAdapter.create(restClient)).build();
        return factory.createClient(UserHttpClient.class);
    }



    @Bean
    CommandLineRunner runner(UserHttpClient client){
        return args -> {
            List<User> users = client.findAll();
            System.out.println(users);

            User user = client.findById(1);
            System.out.println(user);
        };
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
