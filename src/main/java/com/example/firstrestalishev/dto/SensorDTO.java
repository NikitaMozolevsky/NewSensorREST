package com.example.firstrestalishev.dto;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Data
public class SensorDTO implements Cloneable {

    @Size(max = 30, message = "name should have less than 30 characters")
    @Size(min = 3, message = "name should have more than 3 characters")
    private String name;

    public SensorDTO(String name) {
        this.name = name;
    }

    public SensorDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public SensorDTO clone() {
        SensorDTO sensorDTO = new SensorDTO();
        sensorDTO.name = this.name;
        return sensorDTO;
    }
}
