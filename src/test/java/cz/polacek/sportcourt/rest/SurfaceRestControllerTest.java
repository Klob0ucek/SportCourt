package cz.polacek.sportcourt.rest;

import cz.polacek.sportcourt.exceptions.EntityNotFoundException;
import cz.polacek.sportcourt.facade.CourtFacade;
import cz.polacek.sportcourt.facade.SurfaceFacade;
import cz.polacek.sportcourt.utils.TestDataFactory;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatusCode;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.fail;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SurfaceRestControllerTest {
    @Mock
    private SurfaceFacade surfaceFacade;

    @InjectMocks
    private SurfaceRestController surfaceRestController;

    @Test
    void getAllSurfaces_noSurfacesFound_returnsEmptyList() {
        when(surfaceFacade.getAllSurfaces()).thenReturn(Collections.emptyList());

        var result = surfaceRestController.getAllSurfaces();

        assertThat(result.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(200));
        assertThat(result.getBody()).isEqualTo(Collections.emptyList());
    }

    @Test
    void getAllSurfaces_surfacesFound_returnsSurfaces() {
        when(surfaceFacade.getAllSurfaces()).thenReturn(List.of(TestDataFactory.getGrassSurfaceDto()));

        var result = surfaceRestController.getAllSurfaces();

        assertThat(result.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(200));
        assertThat(result.getBody()).isEqualTo(List.of(TestDataFactory.getGrassSurfaceDto()));
    }

    @Test
    void updateSurface_surfaceFound_returnsSurface() {
        var expectedSurface = TestDataFactory.getGrassSurfaceDto();
        when(surfaceFacade.updateSurface(expectedSurface)).thenReturn(expectedSurface);

        var result = surfaceRestController.updateSurface(expectedSurface);

        assertThat(result.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(200));
        assertThat(result.getBody()).isEqualTo(expectedSurface);
    }

    @Test
    void updateSurface_surfaceNotFound_returnsNull() {
        var expectedSurface = TestDataFactory.getGrassSurfaceDto();
        when(surfaceFacade.updateSurface(expectedSurface)).thenThrow(new EntityNotFoundException("Surface not found"));

        try {
            surfaceRestController.updateSurface(expectedSurface);
            fail("Exception should be thrown");
        } catch (EntityNotFoundException e) {
            assertThat(e.getMessage()).isEqualTo("Surface not found");
        }
    }
}
