package ch.wiss.m295.lb_projekt.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex,
            HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        return createDefaultErrorResponse(ex.getMessage());
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
            HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    // Alle selbst defienerten Exception Handlers

    // Liga
    @ExceptionHandler(LigenLoadException.class)
    public ResponseEntity<Object> handleLigenLoadException(LigenLoadException ex, WebRequest request) {
        return createDefaultErrorResponse(ex.getMessage());
    }

    @ExceptionHandler(LigaCouldNotBeFoundException.class)
    public ResponseEntity<Object> handleLigaCouldNotBeFoundException(LigaCouldNotBeFoundException ex,
            WebRequest request) {
        return createDefaultErrorResponse(ex.getMessage());
    }

    @ExceptionHandler(LigaCouldNotBeSavedException.class)
    public ResponseEntity<Object> handleLigaCouldNotBeSavedException(LigaCouldNotBeSavedException ex,
            WebRequest request) {
        return createDefaultErrorResponse(ex.getMessage());
    }

    // Team
    @ExceptionHandler(TeamLoadException.class)
    public ResponseEntity<Object> handleTeamLoadException(TeamLoadException ex, WebRequest request) {
        return createDefaultErrorResponse(ex.getMessage());
    }

    @ExceptionHandler(TeamCouldNotBeFoundException.class)
    public ResponseEntity<Object> handleTeamCouldNotBeFoundException(TeamCouldNotBeFoundException ex,
            WebRequest request) {
        return createDefaultErrorResponse(ex.getMessage());
    }

    @ExceptionHandler(TeamCouldNotBeSavedException.class)
    public ResponseEntity<Object> handleTeamCouldNotBeSavedException(TeamCouldNotBeSavedException ex,
            WebRequest request) {
        return createDefaultErrorResponse(ex.getMessage());
    }

    // Spieler
    @ExceptionHandler(SpielerLoadException.class)
    public ResponseEntity<Object> handleSpielerLoadException(SpielerLoadException ex, WebRequest request) {
        return createDefaultErrorResponse(ex.getMessage());
    }

    @ExceptionHandler(SpielerCouldNotBeFoundException.class)
    public ResponseEntity<Object> handleSpielerCouldNotBeFoundException(SpielerCouldNotBeFoundException ex,
            WebRequest request) {
        return createDefaultErrorResponse(ex.getMessage());
    }

    @ExceptionHandler(SpielerCouldNotBeSavedException.class)
    public ResponseEntity<Object> handleSpielerCouldNotBeSavedException(SpielerCouldNotBeSavedException ex,
            WebRequest request) {
        return createDefaultErrorResponse(ex.getMessage());
    }

    // Unsere standart exception methode
    private ResponseEntity<Object> createDefaultErrorResponse(String exceptionMessage) {
        Map<String, String> errors = new HashMap<>();
        errors.put("error", exceptionMessage);

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}
