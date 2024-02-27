package io.vanillabp.template.usecase;

import io.vanillabp.spi.process.ProcessService;
import io.vanillabp.spi.service.WorkflowService;
import io.vanillabp.template.usecase.aggregate.UseCaseAggregate;
import io.vanillabp.template.usecase.aggregate.UseCaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@WorkflowService(workflowAggregateClass = UseCaseAggregate.class)
@Transactional
public class UseCase {

    @Autowired
    private ProcessService<UseCaseAggregate> processService;

    @Autowired
    private UseCaseRepository usecases;

    public void runUseCase() throws Exception {

        final var usecase = new UseCaseAggregate();

        usecase.setId(Long.toHexString(System.currentTimeMillis()));

        processService.startWorkflow(usecase);

    }

}
