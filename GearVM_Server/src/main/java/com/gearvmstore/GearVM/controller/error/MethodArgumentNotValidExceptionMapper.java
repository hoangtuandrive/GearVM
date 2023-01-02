package com.gearvmstore.GearVM.controller.error;

import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

class MethodArgumentNotValidExceptionMapper {

    public Map<String, List<String>> extractErrorsFrom(MethodArgumentNotValidException exception) {
        var errors = new HashMap<String, List<String>>();
        errors.put("errors", errorsFrom(exception));
        return errors;
    }

    private List<String> errorsFrom(MethodArgumentNotValidException exception) {
        return fieldErrors(exception).stream()
                .map(error -> error.getDefaultMessage())
                .toList();
    }

    private List<FieldError> fieldErrors(MethodArgumentNotValidException exception) {
        return exception.getBindingResult().getFieldErrors();
    }

}