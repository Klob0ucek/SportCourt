package cz.polacek.sportcourt.api;

import cz.polacek.sportcourt.data.model.GameType;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class ReservationDto {
    private Long id;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    private CourtDto court;
    private UserDto user;
    private GameType gameType;
    private double price;

    // TODO add more
}
