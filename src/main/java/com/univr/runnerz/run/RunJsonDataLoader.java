package com.univr.runnerz.run;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aot.hint.TypeReference;
import org.springframework.boot.CommandLineRunner;

import java.io.IOException;
import java.io.InputStream;

public class RunJsonDataLoader implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(RunJsonDataLoader.class);
    private final RunRepository runRepository;
    private final ObjectMapper objectMapper;

    //when you create an instance of that class it will see that is dependent on RunRepository and it will inject it
    public RunJsonDataLoader(RunRepository runRepository, ObjectMapper objectMapper) {
        this.runRepository = runRepository;
        this.objectMapper = objectMapper; //we are reading some json and trying to map that and deserialize in two, into a list of run
    }

    //it's a type of code where you are reading a json file and put the data into a database
    @Override
    public void run(String... args) throws Exception {
        if(runRepository.count() == 0){
            try (InputStream inputStream = TypeReference.class.getResourceAsStream("/data/runs/json")){
                Runs allRuns = objectMapper.readValue(inputStream, Runs.class);
                log.info("Reading {} runs from JSON data and saving to in-memory collection.", allRuns.runs().size());
                runRepository.saveAll(allRuns.runs());
            } catch (IOException e){
                throw new RuntimeException("Failed to read JSON data", e);
            }
        } else {
            log.info("Not loading Runs from JSON data becouse the collection contains data.");
        }

    }
}
