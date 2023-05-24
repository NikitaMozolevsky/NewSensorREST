package com.example.firstrestalishev.configuration;

import com.example.firstrestalishev.dto.MeasurementDTO;
import com.example.firstrestalishev.dto.SensorDTO;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Configuration
public class PrototypeBeans {

    private static String sensorName = "New Sensor";

    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public Map<String, String> randomRainingAndValueDTO() {
        Random random = new Random();
        MeasurementDTO measurementDTO = new MeasurementDTO();
        Map<String, String> map = new HashMap<>();
        double randomNum = Math.round((Math.random() * 60 - 20) * 10) / 10.0;
        map.put("value", String.valueOf(randomNum));
        map.put("raining", String.valueOf(random.nextBoolean()));
        return map;
    }

    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public SensorDTO sensorDTO() {
        SensorDTO sensorDTO = new SensorDTO();
        sensorDTO.setName(sensorName);
        return sensorDTO;
    }

}
