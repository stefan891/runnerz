package com.univr.runnerz.run;


import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

//we have a lot of methods available without typing anycode like findAll
public interface RunRepository extends ListCrudRepository<Run, Integer> {

    //if you want we can create our own query by adding @Query
    List<Run> findAllByLocation(String location);
}
