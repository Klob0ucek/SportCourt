package cz.polacek.sportcourt.service;

import cz.polacek.sportcourt.api.CourtDto;
import cz.polacek.sportcourt.data.model.Court;
import cz.polacek.sportcourt.data.repository.CourtRepository;
import cz.polacek.sportcourt.exceptions.EntityNotFoundException;
import cz.polacek.sportcourt.utils.TestDataFactory;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CourtServiceTest {
    @Mock
    private CourtRepository courtRepository;

    @InjectMocks
    private CourtService courtService;

    @Test
    void getAllCourts_noCourtsFound_returnsEmptyList() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Court> expectedPage = new PageImpl<>(List.of());

        when(courtRepository.findAll(pageable)).thenReturn(expectedPage);

        var found = courtService.getAllCourts(pageable);

        assertThat(found).isEmpty();
    }

    @Test
    void getAllCourts_courtsFound_returnsCourts() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Court> expectedPage = new PageImpl<>(List.of(TestDataFactory.getClayCourt(), TestDataFactory.getGrassCourt()));

        when(courtRepository.findAll(pageable)).thenReturn(expectedPage);

        var result = courtService.getAllCourts(pageable);

        assertThat(result).containsExactly(TestDataFactory.getClayCourt(), TestDataFactory.getGrassCourt());
    }

    @Test
    void getCourtById_courtFound_returnsCourt() {
        var court = TestDataFactory.getClayCourt();

        when(courtRepository.findById(court.getId())).thenReturn(Optional.of(court));

        var result = courtService.getCourtById(court.getId());

        assertThat(result).isEqualTo(court);
    }

    @Test
    void getCourtById_courtNotFound_returnsNull() {
        var court = TestDataFactory.getClayCourt();

        when(courtRepository.findById(court.getId())).thenReturn(Optional.empty());

        try {
            courtService.getCourtById(court.getId());
        } catch (EntityNotFoundException e) {
            assertThat(e.getMessage()).isEqualTo("Did not find court with id: " + court.getId());
        }
    }

    @Test
    void createNewCourt_validCourt_returnsNewCourt() {
        var court = TestDataFactory.getGrassCourt();

        when(courtRepository.save(court)).thenReturn(court);

        var result = courtService.createNewCourt(court);

        assertThat(result).isEqualTo(court);
    }

    @Test
    void updateCourt_validCourt_returnsUpdatedCourt() {
        var court = TestDataFactory.getGrassCourt();

        when(courtRepository.findById(1L)).thenReturn(Optional.of(court));
        when(courtRepository.save(court)).thenReturn(court);

        var updated = courtService.updateCourt(1L, court);

        assertThat(updated).isEqualTo(court);
    }

    @Test
    void updateCourt_invalidCourt_throwsException() {
        var court = TestDataFactory.getGrassCourt();

        when(courtRepository.findById(1L)).thenReturn(Optional.empty());

        try {
            courtService.updateCourt(1L, court);
        } catch (EntityNotFoundException e) {
            assertThat(e.getMessage()).isEqualTo("Did not find court with id: 1");
        }
    }


}
