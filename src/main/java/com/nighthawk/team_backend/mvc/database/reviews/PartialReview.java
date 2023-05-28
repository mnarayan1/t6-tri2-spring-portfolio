package com.nighthawk.team_backend.mvc.database.reviews;


public class PartialReview {
    String assignment;
    double score;
    String ticket;
    String comments;

    // constructor
    public PartialReview(String assignment, double score, String ticket, String comments) {
        this.assignment = assignment;
        this.score = score;
        this.ticket = ticket;
        this.comments = comments;
    }
}
