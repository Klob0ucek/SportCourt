package cz.polacek.sportcourt.rest.exceptionhandling;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
public class ApiError {
    private LocalDateTime timestamp;
    private HttpStatus status;
    private String message;
    private String path;
}
