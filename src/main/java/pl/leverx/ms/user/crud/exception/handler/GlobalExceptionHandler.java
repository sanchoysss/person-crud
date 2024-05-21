package pl.leverx.ms.user.crud.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import pl.leverx.ms.user.crud.dto.ErrorDTO;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDTO> handleError(Exception exception) {
        return ResponseEntity
                .internalServerError()
                .body(new ErrorDTO(HttpStatus.INTERNAL_SERVER_ERROR.value(), exception.getMessage()));
    }
}
