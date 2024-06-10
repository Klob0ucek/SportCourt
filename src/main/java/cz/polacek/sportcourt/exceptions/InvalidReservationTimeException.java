package cz.polacek.sportcourt.exceptions;

import org.springframework.http.HttpStatus;

public class InvalidReservationTimeException extends AbstractApiException{
    public InvalidReservationTimeException(String message) {super(message, HttpStatus.BAD_REQUEST);}
}
