package com.example.vaadin_sensor_app.views.sensor.luminosity;

import com.example.vaadin_sensor_app.views.sensor.Sensor;
import com.vaadin.flow.component.html.Div;

import java.util.Locale;

public class Luminosity extends Sensor<Double, Integer> {

    public Luminosity(Double luminosity, Integer maxLum) {
        super(luminosity, maxLum);
        setClassName("flashlight-container");
        add(title);
        Div luminositySvgContainer = new Div (imageSvgSensor);
        luminositySvgContainer.setClassName("flashlight-svg-container");
        add(luminositySvgContainer);
    }

    @Override
    protected String getSensorTextTitle(Double luminosity, Integer maxLum) {
        return "Luminosity: " + luminosity.intValue();
    }

    @Override
    protected String getSvgString(Double luminosity, Integer maxLum) {
        Double opacity = luminosity/maxLum;

        String svgString = """
                <svg width="200" height="100" viewBox="0 0 200 100">
                  <rect
                    x="20"
                    y="35"
                    width="60"
                    height="30"
                    rx="2"
                    class="flashlight-body"
                  ></rect>
        
                  <polygon
                    points="80,35 100,45 100,55 80,65"
                    class="flashlight-head"
                  ></polygon>
        
                  <circle
                    cx="140"
                    cy="50"
                    r="35"
                    class="flashlight-light-beam"
                    style="opacity: %f"
                  ></circle>
                </svg>
            """;

        return String.format(Locale.US, svgString, opacity);
    }
}
