package java16.taskdto.response;

import java16.taskdto.entityes.Like;
import lombok.Builder;

import java.util.List;
@Builder
public record ProductResponse(
        List<String> images,
        String title,
        String address,
        List<Like> likes
        ) {
}
