package com.example.habitpattern.exception;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice // allows the program to output from this class when met with an error instead of with the default message, this accounts for the entire application
public class GlobExcepHand {

    @ExceptionHandler(MethodArgumentNotValidException.class) // this is what is ran when a validation check is failed
    public ResponseEntity<Map<String, String>> handleValidation(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>(); // allows for the full list of errors to be output by creating a container that is filled by the loop that goes through all errors
        ex.getBindingResult().getFieldErrors()
                .forEach(e -> errors.put(e.getField(), e.getDefaultMessage()));
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(ResponseStatusException.class) // ran through exceptions created manually e.g 404s
    public ResponseEntity<Map<String, String>> handleStatus(ResponseStatusException ex) {
        return ResponseEntity.status(ex.getStatusCode())
                .body(Map.of("error", String.valueOf(ex.getReason())));
    }

     



}

