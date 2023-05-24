package com.example.firstrestalishev.repository;

import com.example.firstrestalishev.entity.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SensorRepository extends JpaRepository<Sensor, Integer> {

    boolean existsByName(String name);

}
