package com.nighthawk.spring_portfolio.mvc.steps;
import com.nighthawk.spring_portfolio.mvc.person.Person;
import com.nighthawk.spring_portfolio.mvc.person.PersonJpaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.text.SimpleDateFormat;

@RestController
@RequestMapping("/api/steps")
public class StepsApiController {
    /*
    #### RESTful API ####
    Resource: https://spring.io/guides/gs/rest-service/
    */

    // Autowired enables Control to connect POJO Object through JPA
    @Autowired
    private PersonJpaRepository repository;



    @GetMapping("/activeDays/{id}")
    public ResponseEntity<String> activeDays(@PathVariable long id) {
        Optional<Person> optional = repository.findById(id);
        if (optional.isPresent()) {  // Good ID
            Person person = optional.get(); // value from findByID
            Steps steps = new Steps(person);
            return new ResponseEntity<>(steps.activeDaysToString(), HttpStatus.OK);
            
        }
        // Bad ID
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);       
        //return -1;
    }

    @GetMapping("/averageSteps/{id}")
    public ResponseEntity<String> averageSteps(@PathVariable long id) {
        Optional<Person> optional = repository.findById(id);
        if (optional.isPresent()) {  // Good ID
            Person person = optional.get(); // value from findByID
            Steps steps = new Steps(person);
            return new ResponseEntity<>(steps.averageStepsToString(), HttpStatus.OK);
            
        }
        // Bad ID
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);       
        //return -1;
    }

    @GetMapping("/averageCalories/{id}")
    public ResponseEntity<String> averageCalories(@PathVariable long id) {
        Optional<Person> optional = repository.findById(id);
        if (optional.isPresent()) {  // Good ID
            Person person = optional.get(); // value from findByID
            Steps steps = new Steps(person);
            return new ResponseEntity<>(steps.averageCaloriesToString(), HttpStatus.OK);
            
        }
        // Bad ID
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);       
        //return -1;
    }

    @GetMapping("/averageMood/{id}")
    public ResponseEntity<String> averageMood(@PathVariable long id) {
        Optional<Person> optional = repository.findById(id);
        if (optional.isPresent()) {  // Good ID
            Person person = optional.get(); // value from findByID
            Steps steps = new Steps(person);
            return new ResponseEntity<>(steps.averageMoodToString(), HttpStatus.OK);
            
        }
        // Bad ID
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);       
        //return -1;
    }

    @GetMapping("/activeCheck/{id}")
    public ResponseEntity<String> activeCheck(@PathVariable long id) {
        Optional<Person> optional = repository.findById(id);
        if (optional.isPresent()) {  // Good ID
            Person person = optional.get(); // value from findByID
            Steps steps = new Steps(person);
            return new ResponseEntity<>(steps.activeCheckToString(), HttpStatus.OK);
            
        }
        // Bad ID
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);       
        //return -1;
    }

   

}