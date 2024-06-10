package cz.polacek.sportcourt.service;

import cz.polacek.sportcourt.data.repository.SurfaceRepository;
import cz.polacek.sportcourt.exceptions.EntityNotFoundException;
import cz.polacek.sportcourt.utils.TestDataFactory;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SurfaceServiceTest {
    @Mock
    private SurfaceRepository surfaceRepository;

    @InjectMocks
    private SurfaceService surfaceService;

    @Test
    void getAllSurfaces_noSurfacesFound_returnsEmptyList() {
        when(surfaceRepository.findAll()).thenReturn(List.of());

        var found = surfaceService.getAllSurfaces();

        assertThat(found).isEmpty();
    }

    @Test
    void getAllSurfaces_surfacesFound_returnsSurfaces() {
        var found = List.of(TestDataFactory.getClaySurface(), TestDataFactory.getGrassSurface());

        when(surfaceRepository.findAll()).thenReturn(found);

        var result = surfaceService.getAllSurfaces();

        assertThat(result).containsExactly(TestDataFactory.getClaySurface(), TestDataFactory.getGrassSurface());
    }

    @Test
    void getSurfaceByType_surfaceFound_returnsSurface() {
        var surface = TestDataFactory.getClaySurface();

        when(surfaceRepository.findById(surface.getType())).thenReturn(Optional.of(surface));

        var result = surfaceService.getSurfaceByType(surface.getType());

        assertThat(result).isEqualTo(surface);
    }

    @Test
    void getSurfaceByType_surfaceNotFound_returnsNull() {
        var surface = TestDataFactory.getClaySurface();

        when(surfaceRepository.findById(surface.getType())).thenReturn(Optional.empty());

        try {
            surfaceService.getSurfaceByType(surface.getType());
        } catch (EntityNotFoundException e) {
            assertThat(e.getMessage()).isEqualTo("Did not find surface with type: CLAY");
        }
    }

    @Test
    void updateSurface_surfaceFound_returnsUpdatedSurface() {
        var newSurface = TestDataFactory.getClaySurface();
        newSurface.setPrice(300);

        when(surfaceRepository.save(newSurface)).thenReturn(newSurface);

        var updated = surfaceService.updateSurface(newSurface);

        assertThat(updated).isEqualTo(newSurface);
    }
}
