package cz.polacek.sportcourt.utils;

import cz.polacek.sportcourt.api.CourtDto;
import cz.polacek.sportcourt.api.ReservationDto;
import cz.polacek.sportcourt.api.SurfaceDto;
import cz.polacek.sportcourt.api.UserDto;
import cz.polacek.sportcourt.api.request.RequestCourtDto;
import cz.polacek.sportcourt.api.request.RequestReservationDto;
import cz.polacek.sportcourt.data.model.Court;
import cz.polacek.sportcourt.data.model.Reservation;
import cz.polacek.sportcourt.data.model.Surface;
import cz.polacek.sportcourt.data.model.SurfaceType;
import cz.polacek.sportcourt.data.model.User;
import java.time.LocalDateTime;

public class TestDataFactory {

    private static LocalDateTime localTime1 = LocalDateTime.of(2024, 1, 1, 8, 0, 0);
    private static LocalDateTime localTime2 = LocalDateTime.of(2024, 1, 1, 12, 0, 0);
    private static LocalDateTime localTime3 = LocalDateTime.of(2024, 1, 1, 16, 0, 0);
    private static LocalDateTime localTime4 = LocalDateTime.of(2024, 1, 1, 20, 0, 0);

    public static SurfaceDto getGrassSurfaceDto(){
        SurfaceDto surfaceDto = new SurfaceDto();
        surfaceDto.setType("GRASS");
        surfaceDto.setPrice(100);
        return surfaceDto;
    }

    public static Surface getGrassSurface(){
        Surface surface = new Surface();
        surface.setPrice(100);
        surface.setType(SurfaceType.GRASS);
        return surface;
    }

    public static SurfaceDto getClaySurfaceDto(){
        SurfaceDto surfaceDto = new SurfaceDto();
        surfaceDto.setType("CLAY");
        surfaceDto.setPrice(150);
        return surfaceDto;
    }

    public static Surface getClaySurface(){
        Surface surface = new Surface();
        surface.setPrice(150);
        surface.setType(SurfaceType.CLAY);
        return surface;
    }


    public static CourtDto getGrassCourtDto(){
        CourtDto courtDto = new CourtDto();
        courtDto.setId(1L);
        courtDto.setSize(500);
        courtDto.setSurface(getGrassSurfaceDto());

        return courtDto;
    }

    public static RequestCourtDto getGrassRequestCourtDto(){
        RequestCourtDto courtDto = new RequestCourtDto();
        courtDto.setSize(500);
        courtDto.setType(SurfaceType.GRASS);

        return courtDto;
    }

    public static Court getGrassCourt(){
        Court court = new Court();
        court.setId(1L);
        court.setSize(500);
        court.setSurface(getGrassSurface());
        return court;
    }

    public static CourtDto getClayCourtDto(){
        CourtDto courtDto = new CourtDto();
        courtDto.setId(2L);
        courtDto.setSize(600);
        courtDto.setSurface(getClaySurfaceDto());

        return courtDto;
    }

    public static RequestCourtDto getClayRequestCourtDto(){
        RequestCourtDto courtDto = new RequestCourtDto();
        courtDto.setSize(600);
        courtDto.setType(SurfaceType.CLAY);

        return courtDto;
    }

    public static Court getClayCourt(){
        Court court = new Court();
        court.setId(2L);
        court.setSize(600);
        court.setSurface(getClaySurface());
        return court;
    }

    public static User getUser() {
        User user = new User();
        user.setId(1L);
        user.setName("John Doe");
        user.setPhoneNumber(727838919L);
        return user;
    }

    public static UserDto getUserDto() {
        UserDto userDto = new UserDto();
        userDto.setName("John Doe");
        userDto.setPhoneNumber(727838919L);
        return userDto;
    }

    public static Reservation getEarlyReservation(){
        Reservation reservation = new Reservation();
        reservation.setId(1L);
        reservation.setCourt(getGrassCourt());
        reservation.setUser(getUser());
        reservation.setStartTime(localTime1);
        reservation.setEndTime(localTime2);
        return reservation;
    }

    public static Reservation getMiddleReservation(){
        Reservation reservation = new Reservation();
        reservation.setId(2L);
        reservation.setCourt(getGrassCourt());
        reservation.setUser(getUser());
        reservation.setStartTime(localTime1);
        reservation.setEndTime(localTime4);
        return reservation;
    }

    public static Reservation getLateReservation(){
        Reservation reservation = new Reservation();
        reservation.setId(3L);
        reservation.setCourt(getGrassCourt());
        reservation.setUser(getUser());
        reservation.setStartTime(localTime3);
        reservation.setEndTime(localTime4);
        return reservation;
    }

    public static Reservation getClayReservation(){
        Reservation reservation = new Reservation();
        reservation.setId(4L);
        reservation.setCourt(getClayCourt());
        reservation.setUser(getUser());
        reservation.setStartTime(localTime1);
        reservation.setEndTime(localTime4);
        return reservation;
    }

    public static ReservationDto getEarlyReservationDto(){
        ReservationDto reservationDto = new ReservationDto();
        reservationDto.setId(1L);
        reservationDto.setCourt(getGrassCourtDto());
        reservationDto.setUser(getUserDto());
        reservationDto.setStartTime(localTime1);
        reservationDto.setEndTime(localTime2);
        return reservationDto;
    }

    public static ReservationDto getMiddleReservationDto(){
        ReservationDto reservationDto = new ReservationDto();
        reservationDto.setId(2L);
        reservationDto.setCourt(getGrassCourtDto());
        reservationDto.setUser(getUserDto());
        reservationDto.setStartTime(localTime1);
        reservationDto.setEndTime(localTime4);
        return reservationDto;
    }

    public static ReservationDto getLateReservationDto(){
        ReservationDto reservationDto = new ReservationDto();
        reservationDto.setId(3L);
        reservationDto.setCourt(getGrassCourtDto());
        reservationDto.setUser(getUserDto());
        reservationDto.setStartTime(localTime3);
        reservationDto.setEndTime(localTime4);
        return reservationDto;
    }

    public static ReservationDto getClayReservationDto(){
        ReservationDto reservationDto = new ReservationDto();
        reservationDto.setId(4L);
        reservationDto.setCourt(getClayCourtDto());
        reservationDto.setUser(getUserDto());
        reservationDto.setStartTime(localTime1);
        reservationDto.setEndTime(localTime4);
        return reservationDto;
    }

    public static RequestReservationDto getEarlyRequestReservationDto(){
        RequestReservationDto reservationDto = new RequestReservationDto();
        reservationDto.setCourtId(1L);
        reservationDto.setPhoneNumber(getUser().getPhoneNumber());
        reservationDto.setName(getUser().getName());
        reservationDto.setStartTime(localTime1);
        reservationDto.setEndTime(localTime2);
        return reservationDto;
    }

    public static RequestReservationDto getMiddleRequestReservationDto(){
        RequestReservationDto reservationDto = new RequestReservationDto();
        reservationDto.setCourtId(1L);
        reservationDto.setPhoneNumber(getUser().getPhoneNumber());
        reservationDto.setName(getUser().getName());
        reservationDto.setStartTime(localTime1);
        reservationDto.setEndTime(localTime4);
        return reservationDto;
    }

    public static RequestReservationDto getLateRequestReservationDto(){
        RequestReservationDto reservationDto = new RequestReservationDto();
        reservationDto.setCourtId(1L);
        reservationDto.setPhoneNumber(getUser().getPhoneNumber());
        reservationDto.setName(getUser().getName());
        reservationDto.setStartTime(localTime3);
        reservationDto.setEndTime(localTime4);
        return reservationDto;
    }

    public static RequestReservationDto getClayRequestReservationDto(){
        RequestReservationDto reservationDto = new RequestReservationDto();
        reservationDto.setCourtId(2L);
        reservationDto.setPhoneNumber(getUser().getPhoneNumber());
        reservationDto.setName(getUser().getName());
        reservationDto.setStartTime(localTime1);
        reservationDto.setEndTime(localTime4);
        return reservationDto;
    }
}
