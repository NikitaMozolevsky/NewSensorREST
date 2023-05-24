package com.example.firstrestalishev.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "measurements")
public class Measurement {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /*@Column(name = "sensor_name")*/
    @ManyToOne
    @JoinColumn(name = "sensor_id", referencedColumnName = "id")
    private Sensor sensor;

    @Max(value = 100, message = "value should be less than 100")
    @Min(value = -100, message = "value should be more than -100")
    @Column(name = "value")
    private Float value;

    @Column(name = "raining")
    private boolean raining;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Measurement that = (Measurement) o;

        if (id != that.id) return false;
        if (raining != that.raining) return false;
        if (!Objects.equals(sensor, that.sensor)) return false;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (sensor != null ? sensor.hashCode() : 0);
        result = 31 * result + (value != null ? value.hashCode() : 0);
        result = 31 * result + (raining ? 1 : 0);
        return result;
    }
}
