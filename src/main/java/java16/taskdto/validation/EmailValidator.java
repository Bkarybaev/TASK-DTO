package java16.taskdto.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class EmailValidator implements ConstraintValidator<EmailValidation,String> {
    @Override
    public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext) {
        return email.endsWith("@gmail.com") ||
                email.endsWith("@mail.ru") ||
                email.endsWith("@yandex.ru") ||
                email.endsWith("@icloud.com");
    }
}
