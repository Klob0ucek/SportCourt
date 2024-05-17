package cz.polacek.sportcourt.exceptions;

import org.springframework.http.HttpStatus;

public class EntityNotFoundException extends AbstractApiException{
    public EntityNotFoundException(String message) {super(message, HttpStatus.NOT_FOUND);}

}
