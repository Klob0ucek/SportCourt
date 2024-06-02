package cz.polacek.sportcourt.rest;

import cz.polacek.sportcourt.api.CourtDto;
import cz.polacek.sportcourt.api.request.RequestCourtDto;
import cz.polacek.sportcourt.facade.CourtFacade;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@OpenAPIDefinition(
        info = @Info(title = "Tennis club court service",
                description = """
                        Simple service for court management. The API has operations for:
                        - getting all courts
                        - getting court by id
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
        public Page<CourtDto> getAllCourts(@ParameterObject Pageable pageable) {
                return courtFacade.getAllCourts(pageable);
        }

        @GetMapping("/{id}")
        public CourtDto getCourt(@PathVariable Long id) {
                return courtFacade.getCourt(id);
        }

        @PostMapping
        public CourtDto makeNewCourt(@ParameterObject RequestCourtDto newCourt) {
                return courtFacade.makeNewCourt(newCourt);
        }

        @PutMapping("/{id}")
        public CourtDto updateCourt(@PathVariable Long id, @RequestBody RequestCourtDto updatedCourt) {
                return courtFacade.updateCourt(id, updatedCourt);
        }

        @DeleteMapping("/{id}")
        public void deleteCourt(@PathVariable Long id) {
                courtFacade.deleteCourt(id);
        }
}
