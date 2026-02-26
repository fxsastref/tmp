package daw.pi.platinum.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // --- 404 / Not Found ---//
    //
    // Username not found
    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ErrorResponse> handlerUsernameNotFoundException(UsernameNotFoundException ex) {
        ErrorResponse error = ErrorResponse.create(ex, HttpStatus.NOT_FOUND, ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    // User not found (triad search)
    @ExceptionHandler(UserNotFoundTriadException.class)
    public ResponseEntity<ErrorResponse> handlerUserNotFoundTriadException(UserNotFoundTriadException ex) {
        ErrorResponse error = ErrorResponse.create(ex, HttpStatus.NOT_FOUND, ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    // SteamId not found
    @ExceptionHandler(SteamIdNotFoundException.class)
    public ResponseEntity<ErrorResponse> handlerSteamIdNotFoundException(SteamIdNotFoundException ex) {
        ErrorResponse error = ErrorResponse.create(ex, HttpStatus.NOT_FOUND, ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    // Game Api Id not found
    @ExceptionHandler(GameApiIdNotFoundException.class)
    public ResponseEntity<ErrorResponse> handlerGameApiIdNotFoundException(GameApiIdNotFoundException ex) {
        ErrorResponse error = ErrorResponse.create(ex, HttpStatus.NOT_FOUND, ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    // --- 400 / Bad Request ---//
    //
    // Username already exists
    @ExceptionHandler(UsernameAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handlerUsernameAlreadyExists(UsernameAlreadyExistsException ex) {
        ErrorResponse error = ErrorResponse.create(ex, HttpStatus.BAD_REQUEST, ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    // Email already exists
    @ExceptionHandler(EmailAlreadyExists.class)
    public ResponseEntity<ErrorResponse> handlerEmailAlreadyExists(EmailAlreadyExists ex) {
        ErrorResponse error = ErrorResponse.create(ex, HttpStatus.BAD_REQUEST, ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
