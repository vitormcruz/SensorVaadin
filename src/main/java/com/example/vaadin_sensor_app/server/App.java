package com.example.vaadin_sensor_app.server;

import com.example.vaadin_sensor_app.views.AppView;
import com.vaadin.flow.component.UI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class App {

    private AppView appView;

    @Autowired
    public App(SensorDataGenerator sensorDataGenerator) {
        sensorDataGenerator.initGenerationReportingOn(this::acceptNewSensorData);
    }

    public void viewCreated(AppView appView){
        this.appView = appView;
    }

    private void acceptNewSensorData(SensorData sensorData) {
        if(appView == null) return;
        System.out.println(sensorData.getAcceleration());
        System.out.println(sensorData.getHumidity());
        System.out.println(sensorData.getLightIntensity());
        System.out.println(sensorData.getPosition().getX());
        System.out.println(sensorData.getPosition().getY());
        System.out.println(sensorData.getPressure());
        System.out.println(sensorData.getTemperature());
        Optional<UI> ui = appView.getUI();
        if (ui.isEmpty()) return;
        ui.get().access(() -> {
            appView.setSensorData(sensorData);
        });
    }

}
