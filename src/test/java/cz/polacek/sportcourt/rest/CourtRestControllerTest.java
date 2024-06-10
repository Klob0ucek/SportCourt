package cz.polacek.sportcourt.rest;

import cz.polacek.sportcourt.api.CourtDto;
import cz.polacek.sportcourt.exceptions.EntityNotFoundException;
import cz.polacek.sportcourt.facade.CourtFacade;
import cz.polacek.sportcourt.utils.TestDataFactory;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatusCode;

import static org.assertj.core.api.AssertionsForClassTypes.fail;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class CourtRestControllerTest {
    @Mock
    private CourtFacade courtFacade;

    @InjectMocks
    private CourtRestController courtRestController;

    @Test
    void getAllCourts_noCourtsFound_returnsEmptyPage() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<CourtDto> expectedPage = new PageImpl<>(List.of());
        when(courtFacade.getAllCourts(pageable)).thenReturn(expectedPage);

        var result = courtRestController.getAllCourts(pageable);

        assertThat(result.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(200));
        assertThat(result.getBody()).isEqualTo(new PageImpl<>(List.of()));
    }

    @Test
    void getAllCourts_courtsFound_returnsCourts() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<CourtDto> expectedPage = new PageImpl<>(List.of(TestDataFactory.getGrassCourtDto(), TestDataFactory.getClayCourtDto()));
        when(courtFacade.getAllCourts(pageable)).thenReturn(expectedPage);

        var result = courtRestController.getAllCourts(pageable);

        assertThat(result.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(200));
        assertThat(result.getBody()).isEqualTo(new PageImpl<>(List.of(TestDataFactory.getGrassCourtDto(), TestDataFactory.getClayCourtDto())));
    }

    @Test
    void getCourt_courtFound_returnsCourt() {
        var expectedCourt = TestDataFactory.getGrassCourtDto();
        when(courtFacade.getCourt(1L)).thenReturn(expectedCourt);

        var result = courtRestController.getCourt(1L);

        assertThat(result.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(200));
        assertThat(result.getBody()).isEqualTo(expectedCourt);
    }

    @Test
    void getCourt_courtNotFound_throwsException() {
        when(courtFacade.getCourt(1L)).thenThrow(new EntityNotFoundException("Court with id 1 not found"));

        try {
            courtRestController.getCourt(1L);
            fail("Exception should be thrown");
        } catch (EntityNotFoundException e) {
            assertThat(e.getMessage()).isEqualTo("Court with id 1 not found");
        }
    }

    @Test
    void makeNewCourt_courtCreated_returnsCourt() {
        var newCourt = TestDataFactory.getGrassRequestCourtDto();
        var expectedCourt = TestDataFactory.getGrassCourtDto();
        when(courtFacade.makeNewCourt(newCourt)).thenReturn(expectedCourt);

        var result = courtRestController.makeNewCourt(newCourt);

        assertThat(result.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(200));
        assertThat(result.getBody()).isEqualTo(expectedCourt);
    }

    @Test
    void updateCourt_courtUpdated_returnsCourt() {
        var updatedCourt = TestDataFactory.getGrassRequestCourtDto();
        var expectedCourt = TestDataFactory.getGrassCourtDto();
        when(courtFacade.updateCourt(1L, updatedCourt)).thenReturn(expectedCourt);

        var result = courtRestController.updateCourt(1L, updatedCourt);

        assertThat(result.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(200));
        assertThat(result.getBody()).isEqualTo(expectedCourt);
    }

    @Test
    void updateCourt_courtNotFound_throwsException() {
        var updatedCourt = TestDataFactory.getGrassRequestCourtDto();
        when(courtFacade.updateCourt(1L, updatedCourt)).thenThrow(new EntityNotFoundException("Court with id 1 not found"));

        try {
            courtRestController.updateCourt(1L, updatedCourt);
            fail("Exception should be thrown");
        } catch (EntityNotFoundException e) {
            assertThat(e.getMessage()).isEqualTo("Court with id 1 not found");
        }
    }

    @Test
    void deleteCourt_courtDeleted() {
        courtRestController.deleteCourt(1L);
    }
}
