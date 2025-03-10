package java16.taskdto.exceptions;

public class PasswordInvalid extends RuntimeException {
    public PasswordInvalid(String message) {
        super(message);
    }
}
