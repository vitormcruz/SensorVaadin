package com.example.vaadin_sensor_app.server;

import org.springframework.stereotype.Service;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

@Service
public class SensorDataGenerator {

    public void initGenerationReportingOn(Consumer<SensorData> reporter){
        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
        scheduler.scheduleAtFixedRate(() -> reporter.accept(SensorData.generateSensorData()), 0, 2, TimeUnit.SECONDS);
    }

}

