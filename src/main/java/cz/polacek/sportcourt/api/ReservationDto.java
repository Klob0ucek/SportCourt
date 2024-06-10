package cz.polacek.sportcourt.api;

import com.fasterxml.jackson.annotation.JsonFormat;
import cz.polacek.sportcourt.data.model.GameType;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class ReservationDto {
    private Long id;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endTime;

    private CourtDto court;
    private UserDto user;
    private GameType gameType;
    private double price;
}
