package io.vanillabp.template.usecase.simulator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "io.vanillabp.template.usecase")
public class UseCaseSimulatorApplication {

    public static void main(
            final String... args) {

        final var app = new SpringApplication(UseCaseSimulatorApplication.class);
        app.run(args);

    }

}
