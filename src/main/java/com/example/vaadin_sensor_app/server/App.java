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
        Optional<UI> ui = appView.getUI();
        if (ui.isEmpty()) return;
        ui.get().access(() -> {
            appView.setSensorData(sensorData);
        });
    }

}
