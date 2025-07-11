package com.example.vaadin_sensor_app.views.sensor.humidity;

import com.example.vaadin_sensor_app.views.sensor.Sensor;
import com.vaadin.flow.component.html.Div;

import java.util.Locale;

public class Humidity extends Sensor<Double, Integer> {

    public Humidity(Double humidity, Integer maxHumidity) {
        super(humidity, maxHumidity);
        setClassName("humidity-drop-container");
        add(title);
        Div luminositySvgContainer = new Div (imageSvgSensor);
        luminositySvgContainer.setClassName("humidity-drop-svg-container");
        add(luminositySvgContainer);
    }

    @Override
    protected String getSensorTextTitle(Double humidity, Integer maxHumidity) {
        return "Humidity: " + humidity.intValue();
    }

    @Override
    protected String getSvgString(Double humidity, Integer maxHumidity) {
        Double fillPercentage = maxHumidity <= 0 ?
                0 : Math.min(100, Math.max(0, (humidity / maxHumidity) * 100 ));

        String svgString = """
            <svg width="100" height"="150" viewBox="0 0 100 150">
              <defs>
                <clipPath id="drop-clip">
                  <path
                    d="M50,0 C50,0 0,60 0,100 C0,130 20,150 50,150 C80,150 100,130 100,100 C100,60 50,0 50,0 Z"
                  ></path>
                </clipPath>
              </defs>
    
              <path
                d="M50,0 C50,0 0,60 0,100 C0,130 20,150 50,150 C80,150 100,130 100,100 C100,60 50,0 50,0 Z"
                class="humidity-drop-background"
              ></path>
    
              <g clip-path="url(#drop-clip)">
                <rect
                  x="0"
                  y="%f"
                  width="100"
                  height="150"
                  class="humidity-drop-fill"
                ></rect>
              </g>
            </svg>
            """;

        Integer height = 150;
        return String.format(Locale.US, svgString, height - (height * fillPercentage / 100));
    }
}
