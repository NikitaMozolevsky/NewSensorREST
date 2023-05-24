package com.example.firstrestalishev.repository;

import com.example.firstrestalishev.entity.Measurement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MeasurementRepository extends JpaRepository<Measurement, Integer> {

    @Query("""
           SELECT sensor.id FROM Sensor sensor where sensor.name = :name
           """)
    int findSensorIdByName(@Param("name") String name);

}
