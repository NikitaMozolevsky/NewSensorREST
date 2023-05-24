package com.example.firstrestalishev.run;

import com.example.firstrestalishev.dto.MeasurementDTO;
import com.example.firstrestalishev.dto.SensorDTO;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class RunFunction {

    private static final String SENSOR_NAME = "New Sensor";
    private static final String ADD_MEASUREMENT = "http://localhost:8088/measurement/add";
    private static final String REGISTER_SENSOR = "http://localhost:8088/sensor/registration";
    private static final String GET_MEASUREMENT = "http://localhost:8088/measurement";
    private static final Random random = new Random();
    private static final RestTemplate restTemplate = new RestTemplate();

    public static void main(String[] args) {

        /*RunFunction.registerSensor(SENSOR_NAME);*/
        RunFunction.add1000RandomMeasures();
        /*RunFunction.get1000Measures();*/

    }

    public static void add1000RandomMeasures() {

        for (int i = 0; i < 1000; i++) {

            makePostRequestWithJSONData(getRandomMeasureMap(), ADD_MEASUREMENT);

        }
    }

    private static Map<String, Object> getRandomMeasureMap() {
        Map<String, Object> map = new HashMap<>();
        double randomNum = Math.round((Math.random() * 60 - 20) * 10) / 10.0;
        map.put("value", randomNum);
        map.put("raining", random.nextBoolean());
        map.put("sensor", new SensorDTO(SENSOR_NAME));
        return map;
    }

    public static void get1000Measures() {


    }

    public static void registerSensor(String sensorName) {

        Map<String, Object> jsonData = new HashMap<>();
        jsonData.put("name", sensorName);
        makePostRequestWithJSONData(jsonData, REGISTER_SENSOR);

    }

    public static void makePostRequestWithJSONData(Map<String, Object> objectMap,
                                                   String url) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Map<String, Object>> request = new HttpEntity<>(objectMap, headers);

        String response = restTemplate.postForObject(url, request, String.class);
        System.out.println(response);

    }

}
