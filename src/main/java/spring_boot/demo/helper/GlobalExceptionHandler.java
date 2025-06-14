package spring_boot.demo.helper;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(errorException.class)
    public ResponseEntity<Map<String, String>> handleCustomException(errorException ex) {
        Map<String, String> errorBody = new HashMap<>();
        errorBody.put("error", ex.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorBody);
    }
}

