package io.vanillabp.template.usecase;

import io.vanillabp.springboot.ModuleAndWorkerAwareSpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackageClasses = UseCaseTestApplication.class)
@EnableJpaRepositories(basePackageClasses = UseCaseTestApplication.class)
public class UseCaseTestApplication {

    public static void main(
            final String... args) {

        final var app = new ModuleAndWorkerAwareSpringApplication(UseCaseTestApplication.class);
        app.run(args);

    }

}
