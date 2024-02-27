package io.vanillabp.template.usecase.startform;

import io.vanillabp.template.usecase.UseCase;
import io.vanillabp.template.usecase.api.v1.StartFormApi;
import io.vanillabp.template.usecase.api.v1.dto.StartFormDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("useCaseStartFormGuiApi")
@RequestMapping("/usecase/api/v1")
public class GuiApiController implements StartFormApi {

    private static final Logger logger = LoggerFactory.getLogger(GuiApiController.class);

    @Autowired
    private UseCase usecaseService;

    @Override
    public ResponseEntity<Void> start(
            final StartFormDto startFormDto) {

        if ((startFormDto == null)
            || !StringUtils.hasText(startFormDto.getValue1())) {
            return ResponseEntity.badRequest().build();
        }

        try {
            usecaseService.runUseCase();
        } catch (Exception e) {
            logger.error("Could not run use-case", e);
            return ResponseEntity.internalServerError().build();
        }

        return ResponseEntity.ok().build();

    }

}
