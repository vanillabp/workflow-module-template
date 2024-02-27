package io.vanillabp.template.usecase.config;

import io.vanillabp.springboot.modules.WorkflowModuleProperties;
import liquibase.integration.spring.SpringLiquibase;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@EnableConfigurationProperties(UseCaseProperties.class)
public class UseCaseConfiguration {

    /*
     * The WorkflowModuleProperties are needed before the properties files are read and must
     * therefore be wrapped in a static class as follows
     */
    @Configuration
    static class UseCaseEarlyBeanConfiguration {
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
