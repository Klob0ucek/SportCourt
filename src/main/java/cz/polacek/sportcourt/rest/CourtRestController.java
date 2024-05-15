package cz.polacek.sportcourt.rest;

import cz.polacek.sportcourt.api.CourtDto;
import cz.polacek.sportcourt.facade.CourtFacade;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@OpenAPIDefinition(
        info = @Info(title = "Tennis club court service",
                description = """
                        Simple service for court management. The API has operations for:
                        - getting all courts
                        - updating court
                        - creating new court
                        - deleting court
                        """
        )
)
@Tag(name = "Court", description = "service for court management")
@RequestMapping(path = "/api/courts", produces = MediaType.APPLICATION_JSON_VALUE)
public class CourtRestController {
        private final CourtFacade courtFacade;
        @Autowired
        public CourtRestController(CourtFacade courtFacade) {
                this.courtFacade = courtFacade;
        }

        @GetMapping
        public Page<CourtDto> getAllTransactions(@ParameterObject Pageable pageable) {
                return courtFacade.getAllCourts(pageable);
        }
}
