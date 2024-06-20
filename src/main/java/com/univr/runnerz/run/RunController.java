package com.univr.runnerz.run;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/runs")
public class RunController {

    //@Autowired is not racommended
    private final RunRepository runRepository;

    //when we create a new instance maybe spring has already this instance, we don't want to create a new instance every time
    //we gonna ask a injection
    public RunController(RunRepository runRepository) {
        this.runRepository = runRepository;
    }

    @GetMapping("")
    List<Run> findAll(){
        return runRepository.findAll();
    }

    //in order to get the id from getmapping we need the pathVariable
    @GetMapping("/{id}")
    Run findById(@PathVariable Integer id){
        return runRepository.findById(id);
    }
}
