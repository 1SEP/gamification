package ru.fsep.enterprise.fseper.controllers;


import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ru.fsep.enterprise.fseper.controllers.dto.ErrorDto;
import ru.fsep.enterprise.fseper.service.exceptions.PostsNotFoundException;
import ru.fsep.enterprise.fseper.service.exceptions.TaskNotAssignedToUserException;
import ru.fsep.enterprise.fseper.service.exceptions.TaskNotFoundException;
import ru.fsep.enterprise.fseper.service.exceptions.UserNotFoundException;

/**
 * Created by Ôëþð on 30.07.2015.
 */
@ControllerAdvice
public class ExceptionHandlerOfRequest extends ResponseEntityExceptionHandler {
    HttpHeaders headers = createContentType();

    @ExceptionHandler({PostsNotFoundException.class})
    public ResponseEntity<Object> handleInvalidPostsRequest(RuntimeException e, WebRequest request) {
        ErrorDto errorDto = createErrorDto("404", e);
        return handleExceptionInternal(e, errorDto, headers, HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler({UserNotFoundException.class})
    public ResponseEntity<Object> handleInvalidUsersRequest(RuntimeException e, WebRequest request) {
        ErrorDto errorDto = createErrorDto("404", e);
        return handleExceptionInternal(e, errorDto, headers, HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler({TaskNotFoundException.class})
    public ResponseEntity<Object> handleInvalidTaskRequest(RuntimeException e, WebRequest request) {
        ErrorDto errorDto = createErrorDto("404", e);
        return handleExceptionInternal(e, errorDto, headers, HttpStatus.NOT_FOUND, request);
    }

    public ErrorDto createErrorDto(String code, RuntimeException e) {
        return new ErrorDto(code, "error", e.getMessage(), e.getClass().getSimpleName());
    }

    public HttpHeaders createContentType() {
        HttpHeaders result = new HttpHeaders();
        result.setContentType(MediaType.APPLICATION_JSON);
        return result;
    }

}
