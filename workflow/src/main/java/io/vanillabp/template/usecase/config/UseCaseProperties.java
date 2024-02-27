package io.vanillabp.template.usecase.config;

import io.vanillabp.springboot.modules.WorkflowModuleIdAwareProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = UseCaseProperties.WORKFLOW_MODULE_ID)
public class UseCaseProperties implements WorkflowModuleIdAwareProperties {

    public static final String WORKFLOW_MODULE_ID = "usecase";

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
