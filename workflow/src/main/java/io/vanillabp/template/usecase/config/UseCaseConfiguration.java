package io.vanillabp.template.usecase.config;

import io.vanillabp.springboot.modules.WorkflowModuleProperties;
import io.vanillabp.springboot.modules.WorkflowModulePropertiesConfiguration;
import liquibase.integration.spring.SpringLiquibase;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.sql.DataSource;


@EnableConfigurationProperties(UseCaseProperties.class)
@ComponentScan(basePackages = "io.vanillabp.template.usecase")
@EntityScan(basePackages = "io.vanillabp.template.usecase")
@EnableJpaRepositories(basePackages = "io.vanillabp.template.usecase")
@AutoConfigureBefore(WorkflowModulePropertiesConfiguration.class)
public class UseCaseConfiguration {

    /*
     * The WorkflowModuleProperties are needed before the properties files are read and must
     * therefore be wrapped in a static class as follows
     */
    @Configuration
    public static class UseCaseEarlyBeanConfiguration {
        @Bean
        public static WorkflowModuleProperties useCaseWorkflowModuleProperties() {
            return new WorkflowModuleProperties(UseCaseProperties.class, UseCaseProperties.WORKFLOW_MODULE_ID);
        }
    }

    @Bean
    @ConfigurationProperties(prefix = "spring.liquibase.usecase")
    public LiquibaseProperties usecaseLiquibaseProperties() {

        return new LiquibaseProperties();

    }

    @Bean
    public SpringLiquibase usecaseLiquibase(
            final DataSource dataSource,
            final LiquibaseProperties usecaseLiquibaseProperties) {

        final var liquibase = new SpringLiquibase();
        liquibase.setDataSource(dataSource);
        liquibase.setChangeLog(usecaseLiquibaseProperties.getChangeLog());
        liquibase.setDatabaseChangeLogTable(usecaseLiquibaseProperties.getDatabaseChangeLogTable());
        liquibase.setDatabaseChangeLogLockTable(usecaseLiquibaseProperties.getDatabaseChangeLogLockTable());
        liquibase.setContexts(usecaseLiquibaseProperties.getContexts());
        liquibase.setDefaultSchema(usecaseLiquibaseProperties.getDefaultSchema());
        liquibase.setLiquibaseSchema(usecaseLiquibaseProperties.getLiquibaseSchema());
        liquibase.setDropFirst(usecaseLiquibaseProperties.isDropFirst());
        liquibase.setShouldRun(usecaseLiquibaseProperties.isEnabled());
//        liquibase.setLabels(usecaseLiquibaseProperties.getLabels());
        liquibase.setChangeLogParameters(usecaseLiquibaseProperties.getParameters());
        liquibase.setRollbackFile(usecaseLiquibaseProperties.getRollbackFile());
        return liquibase;

    }

}
