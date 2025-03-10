package java16.taskdto.request;

import java16.taskdto.enums.RoleUser;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class RegisterRequest {
    private String username;
    private String password;
    private String email;
    private RoleUser role;

}
