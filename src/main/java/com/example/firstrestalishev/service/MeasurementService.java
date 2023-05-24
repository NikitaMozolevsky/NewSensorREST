package com.example.firstrestalishev.service;

import com.example.firstrestalishev.entity.Measurement;
import com.example.firstrestalishev.repository.MeasurementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MeasurementService {

    private final MeasurementRepository measurementRepository;

    @Autowired
    public MeasurementService(MeasurementRepository measurementRepository) {
        this.measurementRepository = measurementRepository;
    }

    @Transactional
    public void addMeasurement(Measurement measurement) {
        int sensorId = measurementRepository
                .findSensorIdByName(measurement.getSensor().getName());
        measurement.getSensor().setId(sensorId);
        measurementRepository.save(measurement);
    }

    public List<Measurement> findAll() {
        return measurementRepository.findAll();
    }

    public List<Measurement> findAllRaining() {
        return measurementRepository.findAll().stream()
                .sorted(Comparator.comparing(Measurement::isRaining))
                .collect(Collectors.toList());
    }

}
