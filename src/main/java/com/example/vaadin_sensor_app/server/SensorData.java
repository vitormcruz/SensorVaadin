package com.example.vaadin_sensor_app.server;

import java.awt.*;
import java.awt.geom.Point2D;

public class SensorData {

    double accelaration;
    double humidity;
    double lightIntensity;
    Point2D position;
    double pressure;
    double temperature;

    public SensorData(double accelaration, double humidity, double lightIntensity, Point2D position,
                      double pressure, double temperature) {
        this.accelaration = accelaration;
        this.humidity = humidity;
        this.lightIntensity = lightIntensity;
        this.position = position;
        this.pressure = pressure;
        this.temperature = temperature;
    }

    public double getAcceleration() {
        return accelaration;
    }

    public double getHumidity() {
        return humidity;
    }

    public double getLightIntensity() {
        return lightIntensity;
    }

    public Point2D getPosition() {
        return position;
    }

    public double getPressure() {
        return pressure;
    }

    public double getTemperature() {
        return temperature;
    }

    public static SensorData generateSensorData() {
        Point2D position =  new Point();
        position.setLocation(Math.random() * 100 * Math.signum(Math.random() - 0.5),
                             Math.random() * 100 * Math.signum(Math.random() - 0.5));
        return new SensorData(Math.random() * 10,
                              Math.random() * 100,
                              Math.random() * 1000,
                              position,
                              Math.random() * 4000,
                              Math.random() * 50
                    );
    }
}
