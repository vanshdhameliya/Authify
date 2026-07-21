package tech.logicforge.authify.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import tech.logicforge.authify.dto.ExceptionResponseDTO;
import tech.logicforge.authify.dto.ValidationExceptionResponseDTO;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<ExceptionResponseDTO> handleEmailAlreadyExistsException(
            EmailAlreadyExistsException ex, HttpServletRequest httpServletRequest) {

        ExceptionResponseDTO exceptionResponse = new ExceptionResponseDTO(
                LocalDateTime.now(),
                HttpStatus.CONFLICT.value(),
                HttpStatus.CONFLICT.getReasonPhrase(),
                ex.getMessage(),
                httpServletRequest.getRequestURI()
        );
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(exceptionResponse);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationExceptionResponseDTO> handleDMethodArgumentNotValidException(
            MethodArgumentNotValidException ex, HttpServletRequest request) {

        Map<String, String> fieldErrors = new HashMap<>();

        ex.getBindingResult().getFieldErrors()
                .forEach(error ->
                        fieldErrors.put(error.getField(), error.getDefaultMessage()));

//        [
//                FieldError(name),
//                FieldError(email),
//                FieldError(password)
//        ]

        ValidationExceptionResponseDTO exceptionResponse = new ValidationExceptionResponseDTO(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                "Validation Failed",
                request.getRequestURI(),
                fieldErrors
        );

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(exceptionResponse);
    }
}
