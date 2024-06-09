package cz.polacek.sportcourt.mappers;

import cz.polacek.sportcourt.api.ReservationDto;
import cz.polacek.sportcourt.api.request.RequestReservationDto;
import cz.polacek.sportcourt.data.model.Reservation;
import cz.polacek.sportcourt.data.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ReservationMapper {
    Reservation mapToReservation(RequestReservationDto requestReservationDto);

    ReservationDto mapToReservationDto(Reservation reservation);

}
