package com.nighthawk.spring_portfolio.mvc.calculator;

// import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
// import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// import java.util.*;
// import java.text.SimpleDateFormat;


@RestController
@RequestMapping("/api/calculator")
public class CalculatorApiController {


    @GetMapping("/{expression}")
    public ResponseEntity<String> getResult(@PathVariable String expression) {

        Calculator specified = new Calculator(expression);
        String result = specified.jsonify();
        if (result != null) {
            return new ResponseEntity<String>(result, HttpStatus.OK);
        }

        // Bad ID
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

    }
    
    @GetMapping("/parentheses/{expression}")
    public ResponseEntity<String> parenthesess(@PathVariable String expression) {

        Calculator equation = new Calculator(expression);
        String finalAnswer = equation.booleantoString();
        return ResponseEntity.ok(finalAnswer);  
    }}
    

