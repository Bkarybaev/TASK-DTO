package java16.taskdto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Setter
@Getter
public class PaginationResponse<T> {
    private List<T> products;
    private int pageNum;
    private int pageSize;
    private long numberOfElements;
    private long numberOfPages;

}
