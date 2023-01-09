package com.nighthawk.spring_portfolio.mvc.lightboard;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/api/lightboard")
public class LightBoardController {

    @GetMapping(value="/{rows}/{columns}")
    public ResponseEntity<JsonNode> getLightBoard(@PathVariable int rows, @PathVariable int columns)
        throws JsonMappingException, JsonProcessingException {

        LightBoard lightBoard = new LightBoard(rows, columns);

        ObjectMapper mapper = new ObjectMapper();
        JsonNode json = mapper.readTree(lightBoard.toString()); // this requires exception handling
  
      return ResponseEntity.ok(json); // JSON response, see ExceptionHandlerAdvice for throws
    }
    
}
