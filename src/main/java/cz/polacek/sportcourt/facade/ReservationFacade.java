package cz.polacek.sportcourt.facade;

import cz.polacek.sportcourt.api.ReservationDto;
import cz.polacek.sportcourt.api.request.RequestReservationDto;
import cz.polacek.sportcourt.mappers.ReservationMapper;
import cz.polacek.sportcourt.service.ReservationService;
import java.util.List;
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
                reservationService.createReservation(newReservation)
        );
    }

    public List<ReservationDto> getAllReservations() {
        return reservationMapper.mapToReservationDtoList(
                reservationService.getAllReservations()
        );
    }

    public ReservationDto getReservationById(Long id) {
        return reservationMapper.mapToReservationDto(
                reservationService.getReservationById(id)
        );
    }

    public void deleteReservation(Long reservationId) {
        reservationService.deleteReservation(reservationId);
    }

    public ReservationDto updateReservation(Long id, RequestReservationDto update) {
        return reservationMapper.mapToReservationDto(
                reservationService.updateReservation(
                        id,
                        update
                )
        );
    }
}
