package kg.kstu.lb.exception;


import kg.kstu.lb.dto.ResponseMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDate;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<ResponseMessage> handleException(final Exception e) {
        String exceptionUUID = getExceptionNumber();
        log.error(e.getMessage() + exceptionUUID, e);
        return buildBaseResponseMessage("Возникла внутренняя ошибка сервера : " + exceptionUUID, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = {BaseException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ResponseMessage> handleBaseException(final BaseException e) {
        String exceptionUUID = getExceptionNumber();
        log.error(e.getMessage() + exceptionUUID, e);
        return buildBaseResponseMessage(e.getMessage() + exceptionUUID, e.getStatus());
    }

    private ResponseEntity<ResponseMessage> buildBaseResponseMessage(String message, HttpStatus status) {
        return new ResponseEntity<>(
                ResponseMessage.builder()
                        .code(status)
                        .details(message)
                        .build(),
                status
        );
    }

    private String getExceptionNumber() {
        return String.format("(%s-%s)", LocalDate.now(), System.currentTimeMillis());
    }
}
