package com.nexai.todolist.aop;

import com.nexai.todolist.utils.Constants;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;


@ControllerAdvice
public class ProjectExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    protected ModelAndView defaultExceptionHandler(HttpServletRequest request, Exception exception, Principal principal) {
        ModelAndView modelAndView = new ModelAndView();
        if (exception.getClass().getSimpleName().equals("AccessDeniedException") && principal == null) {
            modelAndView.setViewName(Constants.LOGIN_PAGE);
        } else {
            modelAndView.addObject(Constants.ERROR_FOR_MODEL, exception.getMessage());
            modelAndView.addObject(Constants.EXCEPTION_NAME_FOR_MODEL, exception.getClass().getSimpleName());
            modelAndView.setViewName(Constants.ERROR_EXCEPTION_PAGE);
        }

        return modelAndView;
    }
}

