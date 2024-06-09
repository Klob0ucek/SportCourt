package cz.polacek.sportcourt.facade;

import cz.polacek.sportcourt.api.ReservationDto;
import cz.polacek.sportcourt.api.request.RequestReservationDto;
import cz.polacek.sportcourt.mappers.ReservationMapper;
import cz.polacek.sportcourt.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservationFacade {
    private final ReservationService reservationService;

    private final ReservationMapper reservationMapper;

    @Autowired
    public ReservationFacade(ReservationService reservationService,
                             ReservationMapper reservationMapper) {
        this.reservationService = reservationService;
        this.reservationMapper = reservationMapper;
    }
    public ReservationDto makeNewReservation(RequestReservationDto newReservation) {
        return reservationMapper.mapToReservationDto(
                reservationService.createReservation(
                        reservationMapper.mapToReservation(newReservation)
                )
        );
    }
}
