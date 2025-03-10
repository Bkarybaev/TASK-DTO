package java16.taskdto.request;

import jakarta.validation.constraints.Email;
import java16.taskdto.validation.EmailValidation;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class LoginDto {
    @EmailValidation
    private String email;
    private String password;
}
