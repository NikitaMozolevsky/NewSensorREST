package com.example.firstrestalishev.util;

import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.List;

@Component
public class BindingResultHandler {

    public StringBuilder getErrorMessage(BindingResult bindingResult) {

        StringBuilder errorMessage = new StringBuilder();

        //Создание строки ошибки
        List<FieldError> errorList = bindingResult.getFieldErrors();
        for (FieldError fieldError : errorList) {
            errorMessage.append(fieldError.getField())
                    .append(" - ")
                    .append(fieldError.getDefaultMessage())
                    .append("; ");
        }

        //бросаем исключение в котором хранится сообщение об ошибке
        return errorMessage;
    }

}
