package cz.polacek.sportcourt.rest;

import cz.polacek.sportcourt.api.CourtDto;
import cz.polacek.sportcourt.api.ReservationDto;
import cz.polacek.sportcourt.api.request.RequestCourtDto;
import cz.polacek.sportcourt.api.request.RequestReservationDto;
import cz.polacek.sportcourt.facade.ReservationFacade;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
    public ReservationDto makeNewCourt(@ParameterObject RequestReservationDto newReservation) {
        return reservationFacade.makeNewReservation(newReservation);
    }
}
