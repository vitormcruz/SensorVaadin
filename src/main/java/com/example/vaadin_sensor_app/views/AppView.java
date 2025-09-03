package com.example.vaadin_sensor_app.views;

import com.example.vaadin_sensor_app.server.App;
import com.example.vaadin_sensor_app.server.SensorData;
import com.example.vaadin_sensor_app.views.sensor.accelerometer.Accelerometer;
import com.example.vaadin_sensor_app.views.sensor.humidity.Humidity;
import com.example.vaadin_sensor_app.views.sensor.luminosity.Luminosity;
import com.example.vaadin_sensor_app.views.sensor.position.Position;
import com.example.vaadin_sensor_app.views.sensor.thermometer.Thermomether;
import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import java.awt.*;

@Route("")
public class AppView extends VerticalLayout {

    private SensorData sensorData = new SensorData(0, 0, 0, new Point(0, 0), 0, 0);
    private final App app;
    private final Humidity humidity;
    private final Accelerometer accelerometer;
    private final Luminosity luminosity;
    private final Thermomether thermomether;
    private final Position position;

    @Autowired
    public AppView(App app) {
        this.app = app;

        Div appDiv = new Div();
        add(appDiv);

        appDiv.setClassName("App");
        appDiv.add(new H1("Vaadin Sensor Application"));
        appDiv.add(new H3("An application created to compare with another one made with React"));
        Div divSensorGrid = new Div();
        divSensorGrid.setClassName("sensor-grid");
        appDiv.add(divSensorGrid);

        Div divSensorRow1 = new Div();
        divSensorRow1.setClassName("sensor-row");
        accelerometer = new Accelerometer(sensorData.getAcceleration(), 10);
        divSensorRow1.add(accelerometer);
        luminosity = new Luminosity(sensorData.getLightIntensity(), 1000);
        divSensorRow1.add(luminosity);
        humidity = new Humidity(sensorData.getHumidity(), 100);
        divSensorRow1.add(humidity);


        Div divSensorRow2 = new Div();
        divSensorRow2.setClassName("sensor-row");
        position = new Position(sensorData.getPosition(), 100);
        divSensorRow2.add(position);


        Div divSensorRow3 = new Div();
        thermomether = new Thermomether(sensorData.getTemperature(), 50);
        divSensorRow3.add(thermomether);
        divSensorRow3.add(new Accelerometer(sensorData.getAcceleration(), 10));

        divSensorGrid.add(divSensorRow1, divSensorRow2, divSensorRow3);

    }

    @Override
    protected void onAttach(AttachEvent attachEvent) {
        app.viewCreated(this);
    }

    public SensorData getSensorData() {
        return sensorData;
    }

    public void setSensorData(SensorData sensorData) {
        accelerometer.changeSensor(sensorData.getAcceleration());
        luminosity.changeSensor(sensorData.getLightIntensity());
        humidity.changeSensor(sensorData.getHumidity());
        thermomether.changeSensor(sensorData.getTemperature());
        position.changeSensor(sensorData.getPosition());
    }

}
