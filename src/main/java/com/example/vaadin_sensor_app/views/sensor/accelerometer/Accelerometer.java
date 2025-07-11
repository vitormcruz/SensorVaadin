package com.example.vaadin_sensor_app.views.sensor.accelerometer;

import com.example.vaadin_sensor_app.views.sensor.Sensor;
import com.vaadin.flow.component.html.Div;

public class Accelerometer extends Sensor<Double, Integer> {


    public Accelerometer(Double acceleration, Integer maxAccel) {
        super(acceleration, maxAccel);
        setClassName("accelerometer-container");
        add(title);
        Div accelerometerSvgContainer = new Div (imageSvgSensor);
        accelerometerSvgContainer.setClassName("accelerometer-svg-container");
        add(accelerometerSvgContainer);
    }

    @Override
    protected String getSensorTextTitle(Double acceleration, Integer maxAccel) {
        return "Acceleration:" + String.format("%.1f", acceleration) + " k/h";
    }

    @Override
    protected String getSvgString(Double acceleration, Integer maxAccel) {
        // Calculate the fill percentage based on min/max values
        double fillPercentage = maxAccel <= 0 ?
                0 : Math.min(100, Math.max(0, (acceleration / maxAccel) * 100));
        // SVG dimensions
        Integer width = 300;
        Integer height = 30;
        Integer fillWidth = (int) ((fillPercentage / 100) * width);

        String svgString = """
            <svg width=%d height=%d>
              <rect
                x="0"
                y="0"
                width=%d
                height=%d
                class="accelerometer-background"
                rx="4"
                ry="4"
              ></rect>
    
              <rect
                x="0"
                y="0"
                width=%d
                height=%d
                class="accelerometer-fill"
                rx="4"
                ry="4"
              ></rect>
            </svg>""";

        return String.format(svgString, width, height, width, height, fillWidth, height);
    }
}
