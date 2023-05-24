package com.example.firstrestalishev.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Getter
@Setter
@ToString
@Entity
@Table(name = "sensors")
public class Sensor {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Size(max = 30, message = "name should have less than 30 characters")
    @Size(min = 3, message = "name should have more than 3 characters")
    @Column(name = "name")
    private String name;
}
