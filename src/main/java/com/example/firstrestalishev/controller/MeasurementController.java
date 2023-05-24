package com.example.firstrestalishev.controller;

import com.example.firstrestalishev.dto.MeasurementDTO;
import com.example.firstrestalishev.dto.SensorDTO;
import com.example.firstrestalishev.entity.Measurement;
import com.example.firstrestalishev.exception.MeasureNotCreatedException;
import com.example.firstrestalishev.service.MeasurementService;
import com.example.firstrestalishev.util.BindingResultHandler;
import com.example.firstrestalishev.util.ErrorResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/measurement")
public class MeasurementController {

    private final MeasurementService measurementService;
    private final ModelMapper modelMapper;
    private final BindingResultHandler bindingResultHandler;

    @Autowired
    public MeasurementController(MeasurementService measurementService,
                                 ModelMapper modelMapper,
                                 BindingResultHandler bindingResultHandler) {
        this.measurementService = measurementService;
        this.modelMapper = modelMapper;
        this.bindingResultHandler = bindingResultHandler;
    }

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> addMeasurement(
            @RequestBody @Valid MeasurementDTO measurementDTO,
            BindingResult bindingResult) {

        Measurement measurement = convertToMeasurement(measurementDTO);

        if (bindingResult.hasErrors()) {
            String errorMessage = bindingResultHandler
                    .getErrorMessage(bindingResult).toString();

            throw new MeasureNotCreatedException(errorMessage);
        }

        measurementService.addMeasurement(measurement);
        return ResponseEntity.ok(HttpStatus.OK);

    }

    @GetMapping()
    public List<MeasurementDTO> findAll() {
        return measurementService.findAll().stream()
                .map(this::convertToMeasurementDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/rainy-days-count")
    public List<MeasurementDTO> findAllRaining() {
        return measurementService.findAllRaining().stream()
                .map(this::convertToMeasurementDTO)
                .collect(Collectors.toList());
    }

    @ExceptionHandler
    private ResponseEntity<ErrorResponse> handleException(MeasureNotCreatedException e) {

        ErrorResponse errorResponse = new ErrorResponse(
                e.getMessage(), System.currentTimeMillis()
        );

        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);

    }

    private Measurement convertToMeasurement(MeasurementDTO measurementDTO) {
        return modelMapper.map(measurementDTO, Measurement.class);
    }

    private MeasurementDTO convertToMeasurementDTO(Measurement measurement) {
        SensorDTO sensorDTO = modelMapper.map(measurement.getSensor(), SensorDTO.class);
        MeasurementDTO measurementDTO = modelMapper.map(measurement, MeasurementDTO.class);
        measurementDTO.setSensor(sensorDTO);
        return measurementDTO;
    }
}
