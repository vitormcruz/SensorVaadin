package com.example.vaadin_sensor_app;

import com.vaadin.flow.component.page.Push;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.component.page.AppShellConfigurator;

@Push
@SpringBootApplication
@Theme("my-theme")
public class VaadinSensorAppApplication implements AppShellConfigurator {

    public static void main(String[] args) {
        SpringApplication.run(VaadinSensorAppApplication.class, args);
    }
}
