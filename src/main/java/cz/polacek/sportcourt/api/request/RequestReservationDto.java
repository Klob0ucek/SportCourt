package cz.polacek.sportcourt.api.request;

import cz.polacek.sportcourt.data.model.GameType;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class RequestReservationDto {
    private Long courtId;
    private Long phoneNumber;
    private String name;
    private GameType gameType;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
}
