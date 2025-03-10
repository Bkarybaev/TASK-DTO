package java16.taskdto.service;

import java16.taskdto.entityes.Product;
import java16.taskdto.enums.Region;
import java16.taskdto.request.ProductRequest;
import java16.taskdto.request.SimpleRequest;
import java16.taskdto.response.PaginationResponse;
import java16.taskdto.response.ProductResponse;

import java.util.List;

public interface ProductService {
    SimpleRequest save(ProductRequest newProduct);

    List<ProductResponse> findAll();

    List<ProductResponse> findAllByRegion(Region region);


    ProductResponse findById(Long id);

    SimpleRequest saveAll(ProductRequest[] newProduct);

    PaginationResponse<ProductResponse> findAllWithPagination(int pageNumber, int sizePage);
}
