package com.example.vaadin_sensor_app.views.sensor.position;

import com.example.vaadin_sensor_app.views.sensor.Sensor;
import com.vaadin.flow.component.html.Div;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Position extends Sensor<Point2D, Integer> {

    private final int width = 300;
    private final int height = 300;
    private final int centerX = width / 2;
    private final int centerY = height / 2;
    private final int maxCoord = 100;

    public Position(Point2D point, Integer maxCoord) {
        super(point, maxCoord);
        setClassName("cartesian-plane-container");
        add(title);
        Div positionSgvContainer = new Div (imageSvgSensor);
        positionSgvContainer.setClassName("cartesian-svg-container");
        add(positionSgvContainer);
    }

    @Override
    protected String getSensorTextTitle(Point2D point, Integer maxCoord) {
        return "Cartesian Plane";
    }

    @Override
    protected String getSvgString(Point2D point, Integer maxCoord) {
        Point2D xStart = toSvgCoords(-maxCoord, 0);
        Point2D xEnd = toSvgCoords(maxCoord, 0);
        Point2D yStart = toSvgCoords(0, -maxCoord);
        Point2D yEnd = toSvgCoords(0, maxCoord);
        Point2D pointPosition = toSvgCoords((int) point.getX(), (int) point.getY());

        String svgString = """
       
        <svg width=%d height=%d viewBox="0 0 %d %d">
          %s
          <line
            x1=%d
            y1=%d
            x2=%d
            y2=%d
            class="axis x-axis"
          />
          <line
            x1=%d
            y1=%d
            x2=%d
            y2=%d
            class="axis y-axis"
          />

          <text x=%d y=%d class="axis-label">X</text>
          <text x=%d y=%d class="axis-label">Y</text>

          <circle
            cx=%f
            cy=%f
            r=5
            class="plot-point"
          />

          <text x=10 y=20 class="coordinates-text">
            X: %d, Y: %d
          </text>
        </svg>
        """;

        return String.format(Locale.US, svgString, width, height, width, height,
                      createSVGGrid(),
                      (int) xStart.getX(), (int) xStart.getY(), (int) xEnd.getX(), (int) xEnd.getY(),
                      (int) yStart.getX(), (int) yStart.getY(), (int) yEnd.getX(), (int) yEnd.getY(),
                      (int) xEnd.getX() - 15, (int) xEnd.getY() + 20,
                      (int) yEnd.getX() - 15, (int) yEnd.getY() + 15,
                      pointPosition.getX(), pointPosition.getY(),
                      (int) pointPosition.getX(), (int) pointPosition.getY());
    }

    public String createSVGGrid(){
        List<String> lines = new ArrayList<>();
        Integer gridSize = 10;
        for (int i = -gridSize; i <= gridSize; i++) {
            // Skip center lines (will be drawn as axes)
            if (i == 0) continue;

            double coord = ((double) i / gridSize) * maxCoord;

            // Horizontal line
            Point2D hStart = toSvgCoords(-maxCoord, coord);
            Point2D hEnd = toSvgCoords(maxCoord, coord);

            // Vertical line
            Point2D vStart = toSvgCoords(coord, -maxCoord);
            Point2D vEnd = toSvgCoords(coord, maxCoord);


            lines.add(String.format(Locale.US, "<line x1=%d y1=%d x2=%d y2=%d class=\"grid-line\" />",
                       (int) hStart.getX(), (int) hStart.getY(), (int) hEnd.getX(), (int) hEnd.getY()));
            lines.add(String.format(Locale.US, "<line x1=%d y1=%d x2=%d y2=%d class=\"grid-line\" />",
                       (int) vStart.getX(), (int) vStart.getY(), (int) vEnd.getX(), (int) vEnd.getY()));

        }

        return lines.stream().reduce("", (a, b) -> a + b);
    }

  public Point2D toSvgCoords(double x, double y){
      // In SVG, (0,0) is top-left corner, so we need to flip Y axis
      return new Point((int) (centerX + (x / maxCoord) * (width * 0.4)), (int) (centerY - (y / maxCoord) * (height * 0.4)));
   };
}
