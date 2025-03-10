package java16.taskdto.response;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@Builder
public class ExceptionResponse {
    private String message;
    private HttpStatus status;
    private String className;
}
