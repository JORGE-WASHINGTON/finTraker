package jwom.fintrak.Exceptions;


import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import jwom.fintrak.DTO.ApiError;
import jwom.fintrak.DTO.ApiValidationError;
import jwom.fintrak.DTO.ValidationError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ApiError> handleEntityNotFoundException(EntityNotFoundException ex, HttpServletRequest request) {
        var apiError = ApiError.builder()
                .statusCode(404)
                .message(ex.getMessage())
                .timestamp(System.currentTimeMillis())
                .path(request.getRequestURI())
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ApiError> handleAuthenticationException(Exception ex, HttpServletRequest request) {
        var apiError = ApiError.builder()
                .statusCode(401)
                .message(ex.getMessage())
                .timestamp(System.currentTimeMillis())
                .path(request.getRequestURI())
                .build();
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(apiError);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex, HttpServletRequest request) {
        List<ValidationError> errors = ex.getFieldErrors().stream().map(error -> new ValidationError(error.getField(), error.getDefaultMessage())).collect(Collectors.toList());

        if (request.getRequestURI().equals("/api/auth/authenticate")) {
            var apiError = ApiError.builder()
                    .statusCode(401)
                    .message("Invalid credentials")
                    .timestamp(System.currentTimeMillis())
                    .path(request.getRequestURI())
                    .build();
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(apiError);
        }

        var apiError = ApiValidationError.builder()
                .statusCode(400)
                .message("Validation error")
                .timestamp(System.currentTimeMillis())
                .errors(errors)
                .path(request.getRequestURI())
                .build();
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(apiError);
    }

    @ExceptionHandler(EntityExistsException.class)
    public ResponseEntity<Object> handleEntityExistsException(EntityExistsException ex, HttpServletRequest request) {
        var apiError = ApiError.builder()
                .statusCode(409)
                .message(ex.getMessage())
                .timestamp(System.currentTimeMillis())
                .path(request.getRequestURI())
                .build();
        return ResponseEntity.status(HttpStatus.CONFLICT).body(apiError);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException ex) {
        System.out.println(ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Account is null");
    }
}
