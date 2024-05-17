package cz.polacek.sportcourt.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public abstract class AbstractApiException extends RuntimeException {
    private final HttpStatus status;

    public AbstractApiException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }
}
