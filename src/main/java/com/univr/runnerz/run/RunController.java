package com.univr.runnerz.run;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/runs")
public class RunController {

    //@Autowired is not racommended
    private final RunRepository runRepository;

    //when we create a new instance maybe spring has already this instance, we don't want to create a new instance every time
    //we're going to ask an injection
    public RunController(RunRepository runRepository) {
        this.runRepository = runRepository;
    }

    @GetMapping("")
    List<Run> findAll(){
        return runRepository.findAll();
    }

    //in order to get the id from getmapping we need the pathVariable
/**
 * This Java function retrieves a Run object by its ID from a repository and throws a
 * RunNotFoundException if the Run is not found.
 * 
 * @param id The `id` parameter in the `@GetMapping` annotation is used to map the endpoint to a
 * specific resource identified by the `id` path variable. In this case, the method `findById` is
 * expecting an `Integer` value for the `id` parameter, which will be used to retrieve
 * @return The code snippet is a method that handles a GET request mapping for a specific `id`. It
 * tries to find a `Run` object in the `runRepository` by the provided `id`. If the `Run` object is
 * found, it is returned. If the `Run` object is not found (i.e., the `Optional` is empty), a
 * `RunNotFoundException` is thrown.
 */
    @GetMapping("/{id}")
    Run findById(@PathVariable Integer id){
        Optional<Run> run = runRepository.findById(id);
        if(run.isEmpty()){
            throw new RunNotFoundException();
        }
        return run.get();
    }

    //post
/**
 * This function creates a new "Run" object by saving it to the runRepository after validating the
 * input data.
 * 
 * @param run The `run` parameter in the `create` method is an object of type `Run`. It is annotated
 * with `@RequestBody`, which means that the data for this object will be obtained from the request
 * body of the HTTP POST request. The `@Valid` annotation is used to indicate that the
 */
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")//need it in order to tell the mapping for post
    void create(@Valid /*we validate that the object will be valid or will throw an exception*/@RequestBody Run run){
        runRepository.save(run);
    }

    //put
/**
 * This Java function updates a Run object in the database based on the provided ID.
 * 
 * @param run The `run` parameter in the `update` method is of type `Run` and is annotated with
 * `@Valid` and `@RequestBody`. This means that the `run` object will be populated with the data from
 * the request body and validated before being passed to the method.
 * @param id The `id` parameter in the `update` method is the identifier of the `Run` object that needs
 * to be updated. It is extracted from the path variable in the URL.
 */
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    void update(@Valid @RequestBody Run run, @PathVariable Integer id){
        runRepository.save(run);
    }

    //update
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    void delete(@PathVariable Integer id){
        runRepository.delete(runRepository.findById(id).get());
    }

    @GetMapping("/location/{location}")
    List<Run> findByLocation(@PathVariable String location){
        return runRepository.findAllByLocation(location);
    }
}
