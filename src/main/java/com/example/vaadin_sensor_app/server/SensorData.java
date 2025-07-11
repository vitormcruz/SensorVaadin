package com.example.vaadin_sensor_app.server;

public class SensorData {

    double accelaration;
    double humidity;
    double lightIntensity;
    SensorPosition position;
    double pressure;
    double temperature;

    public SensorData(double accelaration, double humidity, double lightIntensity, SensorPosition position, double pressure, double temperature) {
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

    public SensorPosition getPosition() {
        return position;
    }

    public double getPressure() {
        return pressure;
    }

    public double getTemperature() {
        return temperature;
    }

    public static SensorData generateSensorData() {
        return new SensorData(Math.random() * 10,
                              Math.random() * 100,
                              Math.random() * 1000,
                              new SensorPosition(Math.random() * 100 * Math.signum(Math.random() - 0.5),
                                                 Math.random() * 100 * Math.signum(Math.random() - 0.5)),
                              Math.random() * 4000,
                              Math.random() * 50
                    );
    }
}
