package com.sotero.Empresa.Errores;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorController {
    public static final String VIEW_ERROR = "error/500";
    @ExceptionHandler(Exception.class)
    public String showInternalServerError(){
        return VIEW_ERROR;
    }
}