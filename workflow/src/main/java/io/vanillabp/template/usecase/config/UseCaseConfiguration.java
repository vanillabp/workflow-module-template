package io.vanillabp.template.usecase.config;

import liquibase.integration.spring.SpringLiquibase;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

@EnableAutoConfiguration
@EnableConfigurationProperties(UseCaseProperties.class)
public class UseCaseConfiguration {


    @Bean
    @ConfigurationProperties(prefix = "spring.liquibase.usecase")
    public LiquibaseProperties usecaseLiquibaseProperties() {

        return new LiquibaseProperties();

    }

    @Bean("liquibase")
    public SpringLiquibase serviceLiquibase(
            final DataSource dataSource) {

        final var properties = serviceLiquibaseProperties();

        final var liquibase = new SpringLiquibase();
        liquibase.setDataSource(dataSource);
        liquibase.setChangeLog(properties.getChangeLog());
        liquibase.setDatabaseChangeLogTable(properties.getDatabaseChangeLogTable());
        liquibase.setDatabaseChangeLogLockTable(properties.getDatabaseChangeLogLockTable());
        liquibase.setContexts(properties.getContexts());
        liquibase.setDefaultSchema(properties.getDefaultSchema());
        liquibase.setLiquibaseSchema(properties.getLiquibaseSchema());
        liquibase.setDropFirst(properties.isDropFirst());
        liquibase.setShouldRun(properties.isEnabled());
        liquibase.setLabels(properties.getLabels());
        liquibase.setChangeLogParameters(properties.getParameters());
        liquibase.setRollbackFile(properties.getRollbackFile());
        return liquibase;

    }

}
