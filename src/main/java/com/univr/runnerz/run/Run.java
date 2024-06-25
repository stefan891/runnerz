package com.univr.runnerz.run;

import jakarta.validation.constraints.NotEmpty;//if we look inside into constrains we have a bunch of options
import jakarta.validation.constraints.Positive;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;

import java.time.LocalDateTime;


//we created a instance of run and it is immutable, we can set them but can't change them
public record Run(
        @Id
        Integer id,
        @NotEmpty
        String title,
        LocalDateTime startedOn,
        LocalDateTime completedOn,
        @Positive
        Integer miles,
        Location location,
        @Version
        Integer version
) {
    public Run{
        if(!completedOn.isAfter(startedOn)){
            throw new IllegalArgumentException("Completed On must be after Starterd On");
        }
    }
}
