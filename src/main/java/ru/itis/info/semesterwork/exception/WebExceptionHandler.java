package ru.itis.info.semesterwork.exception;

import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;

import java.util.logging.Level;

@ControllerAdvice("ru.itis.info.semesterwork.controller")
@Log
public class WebExceptionHandler {

    @ExceptionHandler(value = {ResponseStatusException.class})
    public ModelAndView notFound(ResponseStatusException responseStatusException) {
        log.log(Level.WARNING, "error", "error with status" + responseStatusException.getStatus());
        return setStatus(responseStatusException.getStatus());
    }

    private ModelAndView setStatus(HttpStatus httpStatus) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setStatus(httpStatus);
        return modelAndView;
    }

}
