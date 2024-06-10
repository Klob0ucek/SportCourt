package cz.polacek.sportcourt.rest;

import cz.polacek.sportcourt.api.CourtDto;
import cz.polacek.sportcourt.api.SurfaceDto;
import cz.polacek.sportcourt.facade.CourtFacade;
import cz.polacek.sportcourt.facade.SurfaceFacade;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@OpenAPIDefinition(
        info = @Info(title = "Tennis club court service",
                description = """
                        Simple service for surface management. The API has operations for:
                        - getting all surface types
                        """
        )
)
@Tag(name = "Surface", description = "service for surface management")
@RequestMapping(path = "/api/surface", produces = MediaType.APPLICATION_JSON_VALUE)
public class SurfaceRestController {

        private final SurfaceFacade surfaceFacade;

        @Autowired
        public SurfaceRestController(SurfaceFacade surfaceFacade) {
                this.surfaceFacade = surfaceFacade;
        }

        @GetMapping
        public ResponseEntity<List<SurfaceDto>> getAllCourts() {
                return ResponseEntity.ok(surfaceFacade.getAllSurfaces());
        }

        @PatchMapping
        public ResponseEntity<SurfaceDto> updateSurface(@RequestBody SurfaceDto surfaceDto) {
                return ResponseEntity.ok(surfaceFacade.updateSurface(surfaceDto));
        }
}
