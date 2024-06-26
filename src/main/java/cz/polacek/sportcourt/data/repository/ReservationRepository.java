package cz.polacek.sportcourt.data.repository;

import cz.polacek.sportcourt.data.model.Reservation;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    @Query("SELECT r FROM Reservation r WHERE r.court.id = :courtId AND " +
            "(r.startTime < :endTime AND r.endTime > :startTime)")
    List<Reservation> findOverlappingReservations(@Param("courtId") Long courtId,
                                                  @Param("startTime") LocalDateTime startTime,
                                                  @Param("endTime") LocalDateTime endTime);
}
