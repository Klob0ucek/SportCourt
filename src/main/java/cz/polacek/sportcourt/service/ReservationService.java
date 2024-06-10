package cz.polacek.sportcourt.service;

import cz.polacek.sportcourt.api.request.RequestReservationDto;
import cz.polacek.sportcourt.data.model.Court;
import cz.polacek.sportcourt.data.model.Reservation;
import cz.polacek.sportcourt.data.model.User;
import cz.polacek.sportcourt.data.repository.CourtRepository;
import cz.polacek.sportcourt.data.repository.ReservationRepository;
import cz.polacek.sportcourt.exceptions.EntityNotFoundException;
import cz.polacek.sportcourt.exceptions.InvalidReservationTimeException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;

    private final CourtService courtService;

    private final UserService userService;

    @Autowired
    public ReservationService(ReservationRepository reservationRepository,
                              CourtService courtService,
                              UserService userService) {
        this.reservationRepository = reservationRepository;
        this.courtService = courtService;
        this.userService = userService;
    }

    public Reservation createReservation(RequestReservationDto reservation) {
        if (reservation.getStartTime().isAfter(reservation.getEndTime())) {
            throw new InvalidReservationTimeException("Start time is after end time.");
        }
        if (!findOverlappingReservations(reservation.getCourtId(), reservation.getStartTime(), reservation.getEndTime()).isEmpty()) {
            throw new InvalidReservationTimeException("Reservation already exists for this time.");
        }

        var court = courtService.getCourtById(reservation.getCourtId());
        var user = userService.findOrCreate(reservation.getPhoneNumber(), reservation.getName());

        var newReservation = new Reservation();
        newReservation.setCourt(court);
        newReservation.setUser(user);
        newReservation.setGameType(reservation.getGameType());
        newReservation.setStartTime(reservation.getStartTime());
        newReservation.setEndTime(reservation.getEndTime());
        newReservation.setPrice(getPrice(newReservation));

        System.out.println(reservation);

        return reservationRepository.save(newReservation);
    }

    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    public Reservation getReservationById(Long id) {
        var reservation = reservationRepository.findById(id);
        if (reservation.isPresent()) {
            return reservation.get();
        }
        throw new EntityNotFoundException("Did not find reservation with id: " + id);
    }

    public List<Reservation> findOverlappingReservations(Long courtId, LocalDateTime startTime, LocalDateTime endTime) {
        return reservationRepository.findOverlappingReservations(courtId, startTime, endTime);
    }

    public void deleteReservation(Long reservationId) {
        reservationRepository.deleteById(reservationId);
    }

    public Reservation updateReservation(Long id, RequestReservationDto reservation) {
        if (!findOverlappingReservations(reservation.getCourtId(), reservation.getStartTime(), reservation.getEndTime()).isEmpty()) {
            throw new InvalidReservationTimeException("Reservation already exists for this time.");
        }
        if (reservation.getStartTime().isAfter(reservation.getEndTime())) {
            throw new InvalidReservationTimeException("Start time is after end time.");
        }

        //TODO

        return null;
    }

    private double getPrice(Reservation reservation) {
        Duration duration = Duration.between(reservation.getStartTime(), reservation.getEndTime());
        double hours = duration.toMinutes()/ 60.0;
        double basePrice = hours * reservation.getCourt().getSurface().getPrice();

        return switch (reservation.getGameType()) {
            case SINGLES -> basePrice;
            case DOUBLES -> basePrice * 1.5;
            default -> basePrice;
        };
    }
}
