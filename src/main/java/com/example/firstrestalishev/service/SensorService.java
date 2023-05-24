package com.example.firstrestalishev.service;

import com.example.firstrestalishev.entity.Sensor;
import com.example.firstrestalishev.repository.SensorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SensorService {

    private final SensorRepository sensorRepository;

    @Autowired
    public SensorService(SensorRepository sensorRepository) {
        this.sensorRepository = sensorRepository;
    }

    @Transactional
    public void register(Sensor sensor) {
        sensorRepository.save(sensor);
    }

    public boolean sensorIsExist(String name) {
        return sensorRepository.existsByName(name);
    }
}
