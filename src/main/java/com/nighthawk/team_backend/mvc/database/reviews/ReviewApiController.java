package com.nighthawk.team_backend.mvc.database.reviews;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.nighthawk.team_backend.mvc.database.team.Team;
import com.nighthawk.team_backend.mvc.database.team.TeamJpaRepository;

import java.util.*;



@RestController
@RequestMapping("/api/review")
public class ReviewApiController {
    // Autowired enables Control to connect POJO Object through JPA
    @Autowired
    private ReviewJpaRepository reviewjparepository;

    @Autowired
    private TeamJpaRepository jparepository;

    /*
     * GET List of People
     */
    /*
     * GET individual Team using ID
     */
    @GetMapping("/")
    public ResponseEntity<List<Review>> getReview() {
        return new ResponseEntity<>( reviewjparepository.findAllByOrderByTeam(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<List<Review>> getReview(@PathVariable Long id)
            throws JsonMappingException, JsonProcessingException {
        List<Review> review = reviewjparepository.findAllReviewsById(id);
        return new ResponseEntity<>(review, HttpStatus.OK);
    }

    @PostMapping("/post/{id}")
    public ResponseEntity<Object> postReview(@PathVariable Long id, @RequestBody PartialReview r) {
        Optional<Team> optional = jparepository.findById(id);
        if (optional.isPresent()) { // Good ID
            Team team = optional.get(); // value from findByID
            Review review = new Review(r.assignment, team, r.score, r.ticket, r.comments);
            reviewjparepository.save(review);
            return new ResponseEntity<>("Review for club: " + team.getNames() + " was created successfully",
                HttpStatus.CREATED);
        }

        // Bad ID
        return new ResponseEntity<>("Team not found in team list - Team:" + id, HttpStatus.CREATED);
    }
}