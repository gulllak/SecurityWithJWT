package ru.evgenii.SecurityWithJWT.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.evgenii.SecurityWithJWT.dto.ApiError;

@RestControllerAdvice
public class ErrorHandler {
    @ExceptionHandler
    public ResponseEntity<ApiError> handleEntityNotFound(EntityNotFoundException ex) {
        ApiError apiError = new ApiError(
                HttpStatus.NOT_FOUND,
                ex.getLocalizedMessage()
        );
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

    @ExceptionHandler
    public ResponseEntity<ApiError> handleEntityNotFound(UsernameAlreadyExist ex) {
        ApiError apiError = new ApiError(
                HttpStatus.FORBIDDEN,
                ex.getLocalizedMessage()
        );
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }
}
