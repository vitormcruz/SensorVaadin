package com.example.vaadin_sensor_app.views.sensor;

import com.vaadin.flow.component.Svg;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H3;

public abstract class Sensor<D, M> extends Div {

    protected H3 title;
    protected Svg imageSvgSensor;
    protected M maxVal;

    public Sensor(D data, M maxVal) {
        this.maxVal = maxVal;
        title = new H3(getSensorTextTitle(data, maxVal));
        imageSvgSensor = new Svg(getSvgString(data, maxVal));
    }

    public void changeSensor(D data) {
        title.setText(getSensorTextTitle(data, maxVal));
        imageSvgSensor.setSvg(getSvgString(data, maxVal));
    }

    protected abstract String getSensorTextTitle(D data, M maxVal);

    protected abstract String getSvgString(D data, M maxVal);

}
