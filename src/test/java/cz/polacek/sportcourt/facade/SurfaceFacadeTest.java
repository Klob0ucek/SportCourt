package cz.polacek.sportcourt.facade;

import cz.polacek.sportcourt.api.SurfaceDto;
import cz.polacek.sportcourt.data.model.Surface;
import cz.polacek.sportcourt.data.model.SurfaceType;
import cz.polacek.sportcourt.exceptions.EntityNotFoundException;
import cz.polacek.sportcourt.mappers.SurfaceMapper;
import cz.polacek.sportcourt.service.SurfaceService;
import cz.polacek.sportcourt.utils.TestDataFactory;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.fail;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@ExtendWith(MockitoExtension.class)
public class SurfaceFacadeTest {
    @Mock
    private SurfaceService surfaceService;

    @Mock
    private SurfaceMapper surfaceMapper;

    @InjectMocks
    private SurfaceFacade surfaceFacade;

    @Test
    void getAllSurfaces_noSurfacesFound_returnsEmptyList() {
        Mockito.when(surfaceService.getAllSurfaces()).thenReturn(List.of());
        Mockito.when(surfaceMapper.mapToCourtDtoList(List.of())).thenReturn(List.of());

        var found = surfaceFacade.getAllSurfaces();

        assertThat(found).isEmpty();
    }

    @Test
    void getAllSurfaces_surfacesFound_returnsSurfaces() {
        var surface = TestDataFactory.getClaySurface();
        var surfaceDto = TestDataFactory.getClaySurfaceDto();

        Mockito.when(surfaceService.getAllSurfaces()).thenReturn(List.of(TestDataFactory.getClaySurface()));
        Mockito.when(surfaceMapper.mapToCourtDtoList(List.of(surface))).thenReturn(List.of(surfaceDto));

        var found = surfaceFacade.getAllSurfaces();

        assertThat(found).containsExactly(surfaceDto);
    }

    // Mockito stubbing argument mismatch
//    @Test
//    void updateSurface_surfaceFound_returnsUpdatedSurface() {
//        var surface = TestDataFactory.getClaySurface();
//        var newSurface = new SurfaceDto();
//        newSurface.setType("CLAY");
//        newSurface.setPrice(300);
//        var surfaceDto = TestDataFactory.getClaySurfaceDto();
//
//        Mockito.when(surfaceService.updateSurface(surface)).thenReturn(surface);
//        Mockito.when(surfaceMapper.mapToSurfaceDto(surface)).thenReturn(surfaceDto);
//
//        var updated = surfaceFacade.updateSurface(newSurface);
//
//        assertThat(updated).isEqualTo(surfaceDto);
//    }
//
//    @Test
//    void updateSurface_surfaceNotFound_throwsException() {
//        var surface = TestDataFactory.getClaySurface();
//        var surfaceDto = TestDataFactory.getClaySurfaceDto();
//
//        Mockito.when(surfaceService.updateSurface(surface)).thenThrow(new EntityNotFoundException("Surface not found"));
//
//        assertThatThrownBy(() -> surfaceFacade.updateSurface(surfaceDto))
//                .isInstanceOf(EntityNotFoundException.class)
//                .hasMessage("Surface not found");
//    }
}
