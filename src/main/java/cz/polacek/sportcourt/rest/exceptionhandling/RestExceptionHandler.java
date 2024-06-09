package cz.polacek.sportcourt.rest.exceptionhandling;

import cz.polacek.sportcourt.exceptions.AbstractApiException;
import jakarta.servlet.http.HttpServletRequest;
import java.time.Clock;
import java.time.LocalDateTime;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.util.UrlPathHelper;

@RestControllerAdvice
public class RestExceptionHandler {
    private static final UrlPathHelper URL_PATH_HELPER = new UrlPathHelper();

    @ExceptionHandler({AbstractApiException.class})
    public ResponseEntity<ApiError> handleApiExceptions(final AbstractApiException ex, final HttpServletRequest request) {
        final ApiError apiError = new ApiError(
                LocalDateTime.now(Clock.systemUTC()),
                ex.getStatus(),
                ex.getLocalizedMessage(),
                URL_PATH_HELPER.getRequestUri(request));
        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<ApiError> handleValidationExceptions(final MethodArgumentNotValidException ex, final HttpServletRequest request) {
        final ApiError apiError = new ApiError(
                LocalDateTime.now(Clock.systemUTC()),
                HttpStatus.BAD_REQUEST,
                ex.getLocalizedMessage(),
                URL_PATH_HELPER.getRequestUri(request));
        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
    }
    @ExceptionHandler({Exception.class})
    public ResponseEntity<ApiError> handleAll(final Exception ex, final HttpServletRequest request) {
        final ApiError apiError = new ApiError(
                LocalDateTime.now(Clock.systemUTC()),
                HttpStatus.INTERNAL_SERVER_ERROR,
                getInitialException(ex).getLocalizedMessage(),
                URL_PATH_HELPER.getRequestUri(request));
        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
    }

    private Exception getInitialException(Exception ex) {
        while (ex.getCause() != null) {
            ex = (Exception) ex.getCause();
        }
        return ex;
    }
}
