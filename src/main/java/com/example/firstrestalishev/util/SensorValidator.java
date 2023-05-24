package com.example.firstrestalishev.util;

import com.example.firstrestalishev.entity.Sensor;
import com.example.firstrestalishev.service.SensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class SensorValidator implements Validator {

    private final SensorService sensorService;

    @Autowired
    public SensorValidator(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Sensor.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        Sensor sensor = (Sensor) target;

        if (sensorService.sensorIsExist(sensor.getName())) {
            errors.rejectValue("name", //поля для которого добавляется ошибка
                    "", "Sensor with this name is already registered");
        }

    }


}
