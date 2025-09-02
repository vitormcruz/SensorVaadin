package com.example.vaadin_sensor_app.views.sensor.thermometer;

import com.example.vaadin_sensor_app.views.sensor.Sensor;
import com.vaadin.flow.component.html.Div;

import java.util.Locale;
import java.util.stream.Stream;

public class Thermomether extends Sensor<Double, Integer> {

    public Thermomether(Double temperature, Integer maxTemp) {
        super(temperature, maxTemp);
        setClassName("thermometer-container");
        add(title);
        Div luminositySvgContainer = new Div (imageSvgSensor);
        luminositySvgContainer.setClassName("thermometer-svg-container");
        add(luminositySvgContainer);
    }

    @Override
    protected String getSensorTextTitle(Double temperature, Integer maxTemp) {
        return "Temperature: " + temperature.intValue() + " °C";
    }

    @Override
    protected String getSvgString(Double temperature, Integer maxTemp) {
        Double percentage = Math.min(Math.max(temperature / maxTemp, 0), 1) * 100;

        String svgString = """
            <svg width="50" height="200" viewBox="0 0 50 200">
                <rect x="20" y="10" width="10" height="150" rx="5" ry="5" class="thermometer-outline" ></rect>
                <circle cx="25" cy="160" r="15" class="thermometer-bulb" ></circle>
                <circle cx="25" cy="160" r="13" class="thermometer-mercury" ></circle>
    
                <rect
                    x="22"
                    y=%f
                    width="6"
                    height=%f
                    class="thermometer-mercury"
                />
            """;

        svgString = svgString +
                Stream.of(0, 25, 50, 75, 100)
                      .map((mark) -> {
                          String svgMark = """ 
                             <line
                                  x1 = "30"
                                  y1 = %d
                                  x2 = "35"
                                  y2 = %d
                                  class = "thermometer-marking"></line >
                              <text
                                  x = "38"
                                  y = %d
                                  class = "thermometer-text" >
                                  %d°
                              </text >
                          """;

                          return String.format(Locale.US, svgMark, 10 + (150 * (100 - mark) / 100), 10 + (150 * (100 - mark) / 100), 10 + (150 * (100 - mark) / 100) + 4, (maxTemp * mark / 100));
                      }).reduce((o, o2) -> o + o2).orElse("");

           svgString += "</svg>";


        return String.format(Locale.US, svgString, 10 + (150 - 150 * percentage / 100), 150 * percentage / 100);
    }
}
