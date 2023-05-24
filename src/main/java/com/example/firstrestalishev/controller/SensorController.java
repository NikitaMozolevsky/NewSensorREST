package com.example.firstrestalishev.controller;

import com.example.firstrestalishev.dto.SensorDTO;
import com.example.firstrestalishev.entity.Sensor;
import com.example.firstrestalishev.exception.SensorNotCreatedException;
import com.example.firstrestalishev.service.SensorService;
import com.example.firstrestalishev.util.BindingResultHandler;
import com.example.firstrestalishev.util.ErrorResponse;
import com.example.firstrestalishev.util.SensorValidator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/sensor")
public class SensorController {

    private final SensorService sensorService;
    private final ModelMapper modelMapper;
    private final SensorValidator sensorValidator;
    private final BindingResultHandler bindingResultHandler;

    @Autowired
    public SensorController(SensorService sensorService, ModelMapper modelMapper, SensorValidator sensorValidator, BindingResultHandler bindingResultHandler) {
        this.sensorService = sensorService;
        this.modelMapper = modelMapper;
        this.sensorValidator = sensorValidator;
        this.bindingResultHandler = bindingResultHandler;
    }

    @PostMapping("/registration")
    public ResponseEntity<HttpStatus> registerSensor(@RequestBody @Valid SensorDTO sensorDTO,
                                                     BindingResult bindingResult) {

        Sensor sensor = convertToSensor(sensorDTO);
        sensorValidator.validate(sensor, bindingResult);

        if (bindingResult.hasErrors()) {
            String errorMessage = bindingResultHandler
                    .getErrorMessage(bindingResult).toString();

            throw new SensorNotCreatedException(errorMessage);
        }

        sensorService.register(convertToSensor(sensorDTO));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @ExceptionHandler
    private ResponseEntity<ErrorResponse> handleException(SensorNotCreatedException e) {

        ErrorResponse sensorErrorResponse = new ErrorResponse(
                e.getMessage(), System.currentTimeMillis()
        );

        return new ResponseEntity<>(sensorErrorResponse, HttpStatus.NOT_FOUND);

    }

    @GetMapping("/some")
    public ResponseEntity<HttpStatus> statusResponseEntity() {
        return ResponseEntity.ok(HttpStatus.OK);
    }

    private Sensor convertToSensor(SensorDTO sensorDTO) {
        return modelMapper.map(sensorDTO, Sensor.class);
    }

    private SensorDTO convertToSensorDTO(Sensor sensor) {
        return modelMapper.map(sensor, SensorDTO.class);
    }
}
