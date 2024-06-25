package com.univr.runnerz.run;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aot.hint.TypeReference;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;

@Component
public class RunJsonDataLoader implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(RunJsonDataLoader.class);
    private final JdbcClientRunRepository jdbcClientRunRepository;
    private final ObjectMapper objectMapper;

    //when you create an instance of that class it will see that is dependent on RunRepository and it will inject it
/** This constructor `public RunJsonDataLoader(RunRepository runRepository, ObjectMapper objectMapper)`
 is initializing the `RunJsonDataLoader` class with dependencies `RunRepository` and `ObjectMapper`.
 When an instance of `RunJsonDataLoader` is created, it will automatically inject instances of
 `RunRepository` and `ObjectMapper` into the class, allowing the class to interact with these
 dependencies. In this specific case, `RunJsonDataLoader` needs the `RunRepository` to save the runs
 data to the database and the `ObjectMapper` to read and deserialize JSON data into a list of runs.
*/
    public RunJsonDataLoader(JdbcClientRunRepository jdbcClientRunRepository, ObjectMapper objectMapper) {
        this.jdbcClientRunRepository = jdbcClientRunRepository;
        this.objectMapper = objectMapper; //we are reading in some json and trying to map that and deserialize in two objects, into a list of runs
    }

/**
 * This function checks if the runRepository is empty, reads runs data from a JSON file, and saves it
 * to the database if the repository is empty.
 */
    @Override
    public void run(String... args) throws Exception {
        if(jdbcClientRunRepository.count() == 0){

            try (InputStream inputStream = TypeReference.class.getResourceAsStream("/data/runs.json")){
                Runs allRuns = objectMapper.readValue(inputStream, Runs.class);
                log.info("Reading {} runs from JSON data and saving it to a database", allRuns.runs().size());
                jdbcClientRunRepository.saveAll(allRuns.runs());
            } catch (IOException e){
                throw new RuntimeException("Failed to read JSON data", e);
            }
        } else {
            log.info("Not loading Runs from JSON data becouse the collection contains data.");
        }

    }
}
