package java16.taskdto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
@Getter @Setter
@Builder
public class SimpleRequest {
    private String message;
    private HttpStatus status;
}
