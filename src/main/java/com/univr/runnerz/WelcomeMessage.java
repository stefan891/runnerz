package com.univr.runnerz;

import org.springframework.stereotype.Component;

//with this annotation we are saying that this calss is available to spring boot
@Component
public class WelcomeMessage {


    public String getWelcomeMessage() {
        return "Welcome to spring boot application";
    }
}
