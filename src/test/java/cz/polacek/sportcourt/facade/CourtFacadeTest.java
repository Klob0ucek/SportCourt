package cz.polacek.sportcourt.facade;

import cz.polacek.sportcourt.api.CourtDto;
import cz.polacek.sportcourt.data.model.Court;
import cz.polacek.sportcourt.data.model.SurfaceType;
import cz.polacek.sportcourt.exceptions.EntityNotFoundException;
import cz.polacek.sportcourt.mappers.CourtMapper;
import cz.polacek.sportcourt.service.CourtService;
import cz.polacek.sportcourt.service.SurfaceService;
import cz.polacek.sportcourt.utils.TestDataFactory;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class CourtFacadeTest {
    @Mock
    private CourtService courtService;

    @Mock
    private SurfaceService surfaceService;
    @Mock
    private CourtMapper courtMapper;

    @InjectMocks
    private CourtFacade courtFacade;

    @Test
    void getAllCourts_noCourtsFound_returnsEmptyList() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Court> expectedPage = new PageImpl<>(List.of());
        Page<CourtDto> expectedPageDto = new PageImpl<>(List.of());

        Mockito.when(courtService.getAllCourts(pageable))
                .thenReturn(expectedPage);
        Mockito.when(courtMapper.mapToCourtDtoPage(any()))
                .thenReturn(expectedPageDto);

        var found = courtFacade.getAllCourts(pageable);

        assertThat(found).isEqualTo(expectedPageDto);
    }

    @Test
    void getAllCourts_courtsFound_returnsCourts() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Court> expectedPage = new PageImpl<>(List.of(TestDataFactory.getClayCourt()));
        Page<CourtDto> expectedPageDto = new PageImpl<>(List.of(TestDataFactory.getClayCourtDto()));

        Mockito.when(courtService.getAllCourts(pageable))
                .thenReturn(expectedPage);
        Mockito.when(courtMapper.mapToCourtDtoPage(any()))
                .thenReturn(expectedPageDto);

        var found = courtFacade.getAllCourts(pageable);

        assertThat(found).isEqualTo(expectedPageDto);
    }

    @Test
    void getCourt_courtFound_returnsCourt() {
        var expectedCourt = TestDataFactory.getGrassCourt();
        var expectedCourtDto = TestDataFactory.getGrassCourtDto();

        Mockito.when(courtService.getCourtById(1L))
                .thenReturn(expectedCourt);
        Mockito.when(courtMapper.mapToCourtDto(expectedCourt))
                .thenReturn(expectedCourtDto);

        var found = courtFacade.getCourt(1L);

        assertThat(found).isEqualTo(expectedCourtDto);
    }

    @Test
    void getCourt_courtNotFound_throwsException() {
        Mockito.when(courtService.getCourtById(1L))
                .thenThrow(new EntityNotFoundException("Court with id 1 not found"));

        try {
            courtFacade.getCourt(1L);
        } catch (EntityNotFoundException e) {
            assertThat(e.getMessage()).isEqualTo("Court with id 1 not found");
        }
    }

    @Test
    void makeNewCourt_courtCreated_returnsCourt() {
        var newCourt = TestDataFactory.getGrassRequestCourtDto();
        var expectedCourt = TestDataFactory.getGrassCourt();
        var expectedCourtDto = TestDataFactory.getGrassCourtDto();

        Mockito.when(courtMapper.mapToCourt(newCourt))
                .thenReturn(expectedCourt);
        Mockito.when(surfaceService.getSurfaceByType(SurfaceType.GRASS))
                .thenReturn(TestDataFactory.getGrassSurface());
        Mockito.when(courtService.createNewCourt(expectedCourt))
                .thenReturn(expectedCourt);
        Mockito.when(courtMapper.mapToCourtDto(expectedCourt))
                .thenReturn(expectedCourtDto);

        var found = courtFacade.makeNewCourt(newCourt);

        assertThat(found).isEqualTo(expectedCourtDto);
    }

    @Test
    void updateCourt_courtUpdated_returnsCourt() {
        var updatedCourt = TestDataFactory.getGrassRequestCourtDto();
        var expectedCourt = TestDataFactory.getGrassCourt();
        var expectedCourtDto = TestDataFactory.getGrassCourtDto();

        Mockito.when(courtService.updateCourt(1L, expectedCourt))
                .thenReturn(expectedCourt);
        Mockito.when(courtMapper.mapToCourt(updatedCourt))
                .thenReturn(expectedCourt);
        Mockito.when(courtMapper.mapToCourtDto(expectedCourt))
                .thenReturn(expectedCourtDto);

        var found = courtFacade.updateCourt(1L, updatedCourt);

        assertThat(found).isEqualTo(expectedCourtDto);
    }

    @Test
    void updateCourt_courtNotFound_throwsException() {
        var updatedCourt = TestDataFactory.getGrassRequestCourtDto();
        var mappedCourt = TestDataFactory.getGrassCourt();

        Mockito.when(courtService.updateCourt(1L, mappedCourt))
                .thenThrow(new EntityNotFoundException("Court with id 1 not found"));
        Mockito.when(courtMapper.mapToCourt(updatedCourt))
                .thenReturn(mappedCourt);

        try {
            courtFacade.updateCourt(1L, updatedCourt);
        } catch (EntityNotFoundException e) {
            assertThat(e.getMessage()).isEqualTo("Court with id 1 not found");
        }
    }
}
