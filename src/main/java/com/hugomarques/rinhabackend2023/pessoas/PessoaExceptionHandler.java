package com.hugomarques.rinhabackend2023.pessoas;

import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class PessoaExceptionHandler {

    public static long httpMessageNotReadableException = 0;
    public static long missingServletRequestParameterException = 0;

    @ExceptionHandler(HttpMessageNotReadableException.class)
    protected ResponseEntity<Object> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        httpMessageNotReadableException++;
        return ResponseEntity.unprocessableEntity().build();
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    protected ResponseEntity<Object> handleMissingServletRequestParameterException(MissingServletRequestParameterException ex) {
        missingServletRequestParameterException++;
        return ResponseEntity.badRequest().build();
    }

}