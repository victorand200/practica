package tec.edu.azuay.sale.exceptions;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import tec.edu.azuay.sale.dto.responses.ApiError;

import java.time.LocalDateTime;
import java.util.*;

@RestControllerAdvice
public class GlobalHandlerException {

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<ApiError> handlerObjectNotFoundException(ObjectNotFoundException exception, HttpServletRequest request){
        String backendMessage = exception.getLocalizedMessage();
        String message = Objects.isNull(exception.getMessage()) ? "Object not found" : exception.getMessage();
        ApiError error = createErrorObject(404, message, backendMessage, request);

        return ResponseEntity.status(404).body(error);
    }

    @ExceptionHandler(DuplicatedObjectException.class)
    public ResponseEntity<ApiError> handlerObjectDuplicatedException(DuplicatedObjectException exception, HttpServletRequest request) {
        String backendMessage = exception.getLocalizedMessage();
        String message = Objects.isNull(exception.getMessage()) ? "Duplicated object" : exception.getMessage();
        ApiError error = createErrorObject(409, message, backendMessage, request);

        return ResponseEntity.status(409).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handlerInvalidArgException(MethodArgumentNotValidException exception, HttpServletRequest request) {
        List<String> errorMessages = exception.getBindingResult().getFieldErrors().stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .toList();

        String formattedErrors = String.join(". ", errorMessages);

        ApiError error = createErrorObject(400, "Invalid arguments", formattedErrors, request);

        return ResponseEntity.status(400).body(error);
    }

    private ApiError createErrorObject(int statusCode, String message, String backendMessage, HttpServletRequest request) {
        ApiError error = new ApiError();

        error.setHttpCode(statusCode);
        error.setMessage(Objects.nonNull(message) ? message : "");
        error.setBackendMessage(Objects.nonNull(backendMessage) ? backendMessage : "");
        error.setUrl(request.getRequestURI());
        error.setMethod(request.getMethod());
        error.setTime(LocalDateTime.now());

        return error;
    }
}
