package io.vanillabp.template.usecase.config;

import io.vanillabp.springboot.modules.WorkflowModuleIdAwareProperties;
import io.vanillabp.springboot.modules.WorkflowModuleProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;

@ConfigurationProperties(prefix = UseCaseProperties.WORKFLOW_MODULE_ID)
public class UseCaseProperties implements WorkflowModuleIdAwareProperties {

    public static final String WORKFLOW_MODULE_ID = "usecase";

    @Bean
    public static WorkflowModuleProperties useCaseWorkflowModuleProperties() {

        return new WorkflowModuleProperties(UseCaseProperties.class, UseCaseProperties.WORKFLOW_MODULE_ID);

    }

    @Override
    public String getWorkflowModuleId() {
        return WORKFLOW_MODULE_ID;
    }

    private String myValue;

    public String getMyValue() {
        return myValue;
    }

    public void setMyValue(String myValue) {
        this.myValue = myValue;
    }

}
