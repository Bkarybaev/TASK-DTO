package java16.taskdto.response;
import java16.taskdto.enums.RoleUser;
import lombok.Builder;

@Builder
public record AuthResponse(
        String token,
        String email,
        RoleUser role
) { }
