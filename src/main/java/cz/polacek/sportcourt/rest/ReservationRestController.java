package cz.polacek.sportcourt.rest;

import cz.polacek.sportcourt.api.CourtDto;
import cz.polacek.sportcourt.api.ReservationDto;
import cz.polacek.sportcourt.api.request.RequestCourtDto;
import cz.polacek.sportcourt.api.request.RequestReservationDto;
import cz.polacek.sportcourt.facade.ReservationFacade;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@OpenAPIDefinition(
        info = @Info(title = "Tennis club reservation service",
                description = """
                        Simple service for reservation management. The API has operations for:
                        - creating reservation
                        - getting all reservations
                        - getting reservation by id
                        - updating reservation
                        - deleting reservation
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

    @Operation(summary = "Make new reservation", description = "Make new reservation with data provided by request body.")
    @PostMapping
    public ResponseEntity<ReservationDto> makeNewReservation(@RequestBody RequestReservationDto newReservation) {
        return ResponseEntity.ok(reservationFacade.makeNewReservation(newReservation));
    }

    @Operation(summary = "Get all reservations", description = "Get list of all reservations.")
    @GetMapping
    public ResponseEntity<List<ReservationDto>> getAllReservations() {
        return ResponseEntity.ok(reservationFacade.getAllReservations());
    }

    @Operation(summary = "Get reservation by id", description = "Get a single reservation by id.")
    @GetMapping("/{id}")
    public ResponseEntity<ReservationDto> getReservationById(@PathVariable Long id) {
        return ResponseEntity.ok(reservationFacade.getReservationById(id));
    }

    @Operation(summary = "Delete reservation", description = "Delete reservation by id.")
    @DeleteMapping
    public void deleteReservation(@RequestParam Long reservationId) {
        reservationFacade.deleteReservation(reservationId);
    }

    @Operation(summary = "Update reservation", description = "Update reservation by id with data provided by request body.")
    @PutMapping("/{id}")
    public ResponseEntity<ReservationDto> updateReservation(@PathVariable Long id, @RequestBody RequestReservationDto update) {
        return ResponseEntity.ok(reservationFacade.updateReservation(id, update));
    }
}
