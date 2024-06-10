package cz.polacek.sportcourt.rest;

import cz.polacek.sportcourt.exceptions.EntityNotFoundException;
import cz.polacek.sportcourt.exceptions.InvalidReservationTimeException;
import cz.polacek.sportcourt.facade.ReservationFacade;
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
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)

public class ReservationRestControllerTest {
    @Mock
    private ReservationFacade reservationFacade;

    @InjectMocks
    private ReservationRestController reservationRestController;

    @Test
    void getAllReservations_noReservationsFound_returnsEmptyList() {
        when(reservationFacade.getAllReservations()).thenReturn(Collections.emptyList());

        var result = reservationRestController.getAllReservations();

        assertThat(result.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(200));
        assertThat(result.getBody()).isEqualTo(Collections.emptyList());
    }

    @Test
    void getAllReservations_reservationsFound_returnsReservations() {
        when(reservationFacade.getAllReservations()).thenReturn(List.of(TestDataFactory.getEarlyReservationDto(), TestDataFactory.getLateReservationDto()));

        var result = reservationRestController.getAllReservations();

        assertThat(result.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(200));
        assertThat(result.getBody()).isEqualTo(List.of(TestDataFactory.getEarlyReservationDto(), TestDataFactory.getLateReservationDto()));
    }

    @Test
    void updateReservation_reservationFound_returnsReservation() {
        var requestReservation = TestDataFactory.getClayRequestReservationDto();
        var expectedReservation = TestDataFactory.getClayReservationDto();
        when(reservationFacade.updateReservation(4L, requestReservation)).thenReturn(expectedReservation);

        var result = reservationRestController.updateReservation(4L, requestReservation);

        assertThat(result.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(200));
        assertThat(result.getBody()).isEqualTo(expectedReservation);
    }

    @Test
    void updateReservation_reservationNotFound_throwsException() {
        var requestReservation = TestDataFactory.getClayRequestReservationDto();
        when(reservationFacade.updateReservation(5L, requestReservation)).thenThrow(new EntityNotFoundException("Reservation not found"));

        try {
            reservationRestController.updateReservation(5L, requestReservation);
        } catch (EntityNotFoundException e) {
            assertThat(e.getMessage()).isEqualTo("Reservation not found");
        }
    }

    @Test
    void getReservationById_reservationFound_returnsReservation() {
        var expectedReservation = TestDataFactory.getEarlyReservationDto();
        when(reservationFacade.getReservationById(1L)).thenReturn(expectedReservation);

        var result = reservationRestController.getReservationById(1L);

        assertThat(result.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(200));
        assertThat(result.getBody()).isEqualTo(expectedReservation);
    }

    @Test
    void getReservationById_reservationNotFound_throwsError() {
        when(reservationFacade.getReservationById(1L)).thenThrow(new EntityNotFoundException("Reservation with id 1 not found"));

        try {
            reservationRestController.getReservationById(1L);
        } catch (EntityNotFoundException e) {
            assertThat(e.getMessage()).isEqualTo("Reservation with id 1 not found");
        }
    }

    @Test
    void makeNewReservation_reservationCreated_returnsReservation() {
        var newReservation = TestDataFactory.getClayRequestReservationDto();
        var expectedReservation = TestDataFactory.getClayReservationDto();
        when(reservationFacade.makeNewReservation(newReservation)).thenReturn(expectedReservation);

        var result = reservationRestController.makeNewReservation(newReservation);

        assertThat(result.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(200));
        assertThat(result.getBody()).isEqualTo(expectedReservation);
    }

    @Test
    void makeNewReservation_reservationTimeConflict_throwsError(){
        var newReservation = TestDataFactory.getClayRequestReservationDto();
        when(reservationFacade.makeNewReservation(newReservation)).thenThrow(new InvalidReservationTimeException("Time conflict"));

        try {
            reservationRestController.makeNewReservation(newReservation);
        } catch (InvalidReservationTimeException e) {
            assertThat(e.getMessage()).isEqualTo("Time conflict");
        }
    }
}
