package cz.polacek.sportcourt.rest;

import cz.polacek.sportcourt.api.CourtDto;
import cz.polacek.sportcourt.api.ReservationDto;
import cz.polacek.sportcourt.api.request.RequestCourtDto;
import cz.polacek.sportcourt.api.request.RequestReservationDto;
import cz.polacek.sportcourt.facade.ReservationFacade;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@OpenAPIDefinition(
        info = @Info(title = "Tennis club reservation service",
                description = """
                        Simple service for reservation management. The API has operations for:
                        - creating reservation
                        """
        )
)
@Tag(name = "Reservation", description = "service for reservation management")
@RequestMapping(path = "/api/reservations", produces = MediaType.APPLICATION_JSON_VALUE)
public class ReservationRestController {
    private final ReservationFacade reservationFacade;

    @Autowired
    public ReservationRestController(ReservationFacade reservationFacade) {
        this.reservationFacade = reservationFacade;
    }
    @PostMapping
    public ReservationDto makeNewReservation(@ParameterObject RequestReservationDto newReservation) {
        return reservationFacade.makeNewReservation(newReservation);
    }

    @GetMapping
    public List<ReservationDto> getAllReservations() {
        return reservationFacade.getAllReservations();
    }

    @GetMapping("/{id}")
    public ReservationDto getReservationById(@PathVariable Long id) {
        return reservationFacade.getReservationById(id);
    }

    @DeleteMapping
    public void deleteReservation(@RequestParam Long reservationId) {
        reservationFacade.deleteReservation(reservationId);
    }

    @PostMapping("/{id}")
    public ReservationDto updateReservation(@PathVariable Long id, @ParameterObject RequestReservationDto update) {
        return reservationFacade.updateReservation(id, update);
    }

}
