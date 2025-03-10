package java16.taskdto.exceptions;

import java16.taskdto.response.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {EmailInvalid.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionResponse handleEmailInvalid(EmailInvalid e) {
        return ExceptionResponse.builder()
                .message(e.getMessage())
                .status(HttpStatus.CONFLICT)
                .className(e.getClass().getSimpleName())
                .build();
    }

    @ExceptionHandler(value = {PasswordInvalid.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ExceptionResponse handlePasswordInvalid(PasswordInvalid e) {
        return ExceptionResponse.builder()
                .message(e.getMessage())
                .status(HttpStatus.CONFLICT)
                .className(e.getClass().getSimpleName())
                .build();
    }

    @ExceptionHandler(value = {UserNotFound.class})
    @ResponseStatus(HttpStatus.CONFLICT)
    public ExceptionResponse handleUserNotFound(UserNotFound e) {
        return ExceptionResponse.builder()
                .message(e.getMessage())
                .status(HttpStatus.CONFLICT)
                .className(e.getClass().getSimpleName())
                .build();
    }
}
