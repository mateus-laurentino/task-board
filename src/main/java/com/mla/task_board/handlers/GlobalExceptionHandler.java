package com.mla.task_board.handlers;

import com.mla.task_board.domain.dtos.base.ResultViewModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Arrays;
import java.util.MissingResourceException;
import java.util.Objects;


@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResultViewModel<Boolean>> handleGenericException(Exception ex) {
        logger.error(ex.getMessage(), ex);

        var result = new ResultViewModel<>(false).addErrors("Ocorreu um erro, tente novamente mais tarde");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(result);
    }

    @ExceptionHandler(MissingResourceException.class)
    public ResponseEntity<ResultViewModel<Boolean>> handleGenericException2(MissingResourceException ex) {
        logger.error(ex.getMessage(), ex);

        var result = new ResultViewModel<>(false).addErrors("Ocorreu um erro, tente novamente mais tarde");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(result);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResultViewModel<?>> handleValidationErrors(MethodArgumentNotValidException ex) {
        var errors = new ResultViewModel<>();

        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            var key = error.getDefaultMessage();
            Object maxArg = Arrays.stream(Objects.requireNonNull(error.getArguments()))
                    .filter(arg -> arg instanceof Number)
                    .map(arg -> ((Number) arg).longValue())
                    .max(Long::compareTo)
                    .orElse(0L);

            var erro = Message.getErro(key, error.getField(), maxArg);
            errors.addErrors(erro);
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }
}
