package com.univr.runnerz.run;

import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;


//we created a instance of run and it is immutable, we can set them but can't change them
public record Run(
        Integer id,
        String title,
        LocalDateTime startedOn,
        LocalDateTime completedOn,
        Integer miles,
        Location location
) {
}
