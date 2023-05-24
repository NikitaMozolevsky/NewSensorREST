package com.example.firstrestalishev.dto;

import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Data
public class MeasurementDTO {

    private SensorDTO sensor;

    @Max(value = 100, message = "value should be less than 100")
    @Min(value = -100, message = "value should be more than -100")
    private Float value;

    @Column(name = "raining")
    private boolean raining;

}
