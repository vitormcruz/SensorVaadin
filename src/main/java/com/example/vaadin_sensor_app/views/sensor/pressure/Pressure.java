package com.example.vaadin_sensor_app.views.sensor.pressure;

import com.example.vaadin_sensor_app.views.sensor.Sensor;
import com.vaadin.flow.component.html.Div;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Pressure extends Sensor<Double, Integer> {

    private final int width = 300;
    private final int height = 300;
    private final int centerX = width / 2;
    private final int centerY = height / 2;
    private final int maxCoord = 100;

    public Pressure(Double pressure, Integer maxCoord) {
        super(pressure, maxCoord);
        setClassName("pressure-container");
        add(title);
        Div positionSgvContainer = new Div (imageSvgSensor);
        positionSgvContainer.setClassName("pressure-svg-container");
        add(positionSgvContainer);
    }

    @Override
    protected String getSensorTextTitle(Double pressure, Integer maxPressure) {
        return "Pressure";
    }

    @Override
    protected String getSvgString(Double pressure, Integer maxPressure) {
        double percentage = pressure / maxPressure;
        double angle = 180 + (percentage * 360);

        double width = 200;
        double height = 200;
        double centerX = width / 2;
        double centerY = height / 2;
        double radius = 80;

        String svgString = """
            <svg width=%f height=%f viewBox='0 0 %f %f'}>
                <circle cx=%f cy=%f r=%f class="gauge-face" />
                
                %s
                    
                <text x=%f y=%f class="gauge-value-text"
                      style='font-size: 12px; font-weight: bold;'>
                    %.1f psi
                </text>
    
                <text x=%f y=%f class="gauge-value-text">
                    Pressure (psi)
                </text>
    
                <line x1=%f y1=%f x2=%f y2=%f class="gauge-pointer"
                     style="transform: rotate(%fdeg) " />
    
                <circle cx=%f cy=%f r=5 class="gauge-center" />
        </svg>
        """;

        return String.format(Locale.US, svgString, width, height, width, height, centerX, centerY, radius,
                             createTicks(centerX, centerY, radius, maxPressure),
                             centerX, centerY + 30, pressure, centerX, centerY - 20, centerX, centerY, centerX,
                             centerY - radius + 10, angle, centerX, centerY);

    }

    public String createTicks(double centerX, double centerY, double radius, double maxPressure) {
        List<String> ticks = new ArrayList<>();
        int numTicks = 10;

        for (int i = 0; i < numTicks; i++) {
            // Calculate angle for each tick (starting from top, going clockwise)
            double tickAngle = 90 + (i * 360.0 / numTicks);
            double tickRadian = Math.toRadians(tickAngle);

            double outerX = centerX + (radius * Math.cos(tickRadian));
            double outerY = centerY + (radius * Math.sin(tickRadian));
            double innerX = centerX + ((radius - 10) * Math.cos(tickRadian));
            double innerY = centerY + ((radius - 10) * Math.sin(tickRadian));

            // Add tick line as SVG string
            ticks.add(String.format(Locale.US,
                    "<line x1=\"%.2f\" y1=\"%.2f\" x2=\"%.2f\" y2=\"%.2f\" class=\"gauge-tick\" />",
                    innerX, innerY, outerX, outerY
            ));

            // Add tick label as SVG string
            int labelValue = (int) Math.round(i * (maxPressure / numTicks));
            double labelX = centerX + ((radius - 20) * Math.cos(tickRadian));
            double labelY = centerY + ((radius - 20) * Math.sin(tickRadian));

            ticks.add(String.format(Locale.US, "<text x=\"%.2f\" y=\"%.2f\" class=\"gauge-tick-label\">%d</text>",
                      labelX, labelY + 3, labelValue));
        }

        return ticks.stream().reduce("", (a, b) -> a + b);
    }

}
