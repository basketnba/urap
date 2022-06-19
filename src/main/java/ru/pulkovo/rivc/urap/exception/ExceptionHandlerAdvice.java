package ru.pulkovo.rivc.urap.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.pulkovo.rivc.urap.util.DateTimeUtil;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@ControllerAdvice
public class ExceptionHandlerAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleMethodArgumentNotValidException(Exception exception) {
        List<String> details = new ArrayList<>();
        details.add(exception.getMessage());
        ApiError err = new ApiError(DateTimeUtil.getCurrentTimeInUTC(), "Method Argument Not Valid", details);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Object> handleIllegalArgumentException(Exception exception) {
        List<String> details = new ArrayList<>();
        details.add(exception.getMessage());
        ApiError err = new ApiError(DateTimeUtil.getCurrentTimeInUTC(), "Illegal Argument", details);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }
}