package com.example.vaadin_sensor_app.views;

import com.example.vaadin_sensor_app.server.App;
import com.example.vaadin_sensor_app.server.SensorData;
import com.example.vaadin_sensor_app.server.SensorPosition;
import com.example.vaadin_sensor_app.views.sensor.accelerometer.Accelerometer;
import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

@Route("")
public class AppView extends VerticalLayout {

    private final App app;
    private final Accelerometer accelerometer;
    private SensorData sensorData = new SensorData(0, 0, 0, new SensorPosition(0, 0), 0, 0);

    @Autowired
    public AppView(App app) {
        this.app = app;

        Div appDiv = new Div();
        add(appDiv);

        appDiv.setClassName("App");
        appDiv.add(new H1("Vaadin Sensor Application"));
        appDiv.add(new H3("An application created to compare another one made with React"));
        Div divSensorGrid = new Div();
        divSensorGrid.setClassName("sensor-grid");
        appDiv.add(divSensorGrid);

        Div divSensorRow1 = new Div();
        divSensorRow1.setClassName("sensor-row");
        accelerometer = new Accelerometer(sensorData.getAcceleration(), 10);
        divSensorRow1.add(accelerometer);
        divSensorRow1.add(new Accelerometer(sensorData.getAcceleration(), 10));
        divSensorRow1.add(new Accelerometer(sensorData.getAcceleration(), 10));


        Div divSensorRow2 = new Div();
        divSensorRow2.setClassName("sensor-row");
        divSensorRow2.add(new Accelerometer(sensorData.getAcceleration(), 10));


        Div divSensorRow3 = new Div();
        divSensorRow3.add(new Accelerometer(sensorData.getAcceleration(), 10));
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
    }

}
