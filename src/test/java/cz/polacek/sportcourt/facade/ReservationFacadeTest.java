package cz.polacek.sportcourt.facade;

import cz.polacek.sportcourt.exceptions.EntityNotFoundException;
import cz.polacek.sportcourt.exceptions.InvalidReservationTimeException;
import cz.polacek.sportcourt.mappers.ReservationMapper;
import cz.polacek.sportcourt.service.ReservationService;
import cz.polacek.sportcourt.utils.TestDataFactory;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.assertj.core.api.Fail.fail;

@ExtendWith(MockitoExtension.class)
public class ReservationFacadeTest {
    @Mock
    private ReservationService reservationService;

    @Mock
    private ReservationMapper reservationMapper;

    @InjectMocks
    private ReservationFacade reservationFacade;

    @Test
    void getAllReservations_noReservationsFound_returnsEmptyList() {
        Mockito.when(reservationService.getAllReservations()).thenReturn(List.of());
        Mockito.when(reservationMapper.mapToReservationDtoList(List.of())).thenReturn(List.of());

        var found = reservationFacade.getAllReservations();

        assertThat(found).isEmpty();
    }

    @Test
    void getAllReservations_reservationsFound_returnsReservations() {
        var reservation = TestDataFactory.getClayReservation();
        var reservationDto = TestDataFactory.getClayReservationDto();

        Mockito.when(reservationService.getAllReservations()).thenReturn(List.of(reservation));
        Mockito.when(reservationMapper.mapToReservationDtoList(List.of(reservation))).thenReturn(List.of(reservationDto));

        var found = reservationFacade.getAllReservations();

        assertThat(found).containsExactly(reservationDto);
    }

    @Test
    void createReservation_reservationCreated_returnsReservation() {
        var reservation = TestDataFactory.getClayReservation();
        var reservationDto = TestDataFactory.getClayReservationDto();
        var reservationRequest = TestDataFactory.getClayRequestReservationDto();

        Mockito.when(reservationService.createReservation(reservationRequest)).thenReturn(reservation);
        Mockito.when(reservationMapper.mapToReservationDto(reservation)).thenReturn(reservationDto);

        var found = reservationFacade.makeNewReservation(reservationRequest);

        assertThat(found).isEqualTo(reservationDto);
    }

    @Test
    void createReservation_reservationNotCreated_throwsException() {
        var reservationRequest = TestDataFactory.getClayRequestReservationDto();

        Mockito.when(reservationService.createReservation(reservationRequest)).thenThrow(new InvalidReservationTimeException("Reservation not created"));

        try {
            reservationFacade.makeNewReservation(reservationRequest);
            fail("Exception should be thrown");
        } catch (InvalidReservationTimeException e) {
            assertThat(e).hasMessage("Reservation not created");
        }
    }

    @Test
    void updateReservation_reservationUpdated_returnsUpdatedReservation() {
        var reservation = TestDataFactory.getClayReservation();
        var reservationDto = TestDataFactory.getClayReservationDto();
        var reservationRequest = TestDataFactory.getClayRequestReservationDto();

        Mockito.when(reservationService.updateReservation(4L, reservationRequest)).thenReturn(reservation);
        Mockito.when(reservationMapper.mapToReservationDto(reservation)).thenReturn(reservationDto);

        var updated = reservationFacade.updateReservation(4L, reservationRequest);

        assertThat(updated).isEqualTo(reservationDto);
    }

    @Test
    void updateReservation_reservationNotUpdated_throwsException() {
        var reservationRequest = TestDataFactory.getClayRequestReservationDto();

        Mockito.when(reservationService.updateReservation(4L, reservationRequest)).thenThrow(new InvalidReservationTimeException("Reservation not updated"));

        try {
            reservationFacade.updateReservation(4L, reservationRequest);
            fail("Exception should be thrown");
        } catch (InvalidReservationTimeException e) {
            assertThat(e).hasMessage("Reservation not updated");
        }
    }

    @Test
    void getReservationById_reservationFound_returnsReservation() {
        var reservation = TestDataFactory.getClayReservation();
        var reservationDto = TestDataFactory.getClayReservationDto();

        Mockito.when(reservationService.getReservationById(1L)).thenReturn(reservation);
        Mockito.when(reservationMapper.mapToReservationDto(reservation)).thenReturn(reservationDto);

        var found = reservationFacade.getReservationById(1L);

        assertThat(found).isEqualTo(reservationDto);
    }

    @Test
    void getReservationById_reservationNotFound_throwsException() {
        Mockito.when(reservationService.getReservationById(1L)).thenThrow(new EntityNotFoundException("Reservation with id 1 not found"));

        try {
            reservationFacade.getReservationById(1L);
            fail("Exception should be thrown");
        } catch (EntityNotFoundException e) {
            assertThat(e).hasMessage("Reservation with id 1 not found");
        }
    }
}
