package java16.taskdto.service.impl;

import jakarta.validation.constraints.AssertTrue;
import java16.taskdto.entityes.Product;
import java16.taskdto.enums.Region;
import java16.taskdto.repo.ProductRepo;
import java16.taskdto.request.ProductRequest;
import java16.taskdto.request.SimpleRequest;
import java16.taskdto.response.PaginationResponse;
import java16.taskdto.response.ProductResponse;
import java16.taskdto.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class ProductSerImpl implements ProductService {
    private final ProductRepo productRepo;

    //save product
    @Override
    public SimpleRequest save(ProductRequest newProduct) {
        productRepo.save(newProduct.save());
        return SimpleRequest.builder()
                .message("Product saved")
                .status(HttpStatus.OK)
                .build();
    }


    //get all
    @Override
    public List<ProductResponse> findAll() {

        List<Product> all = productRepo.findAll();

        //get all productResponse
      return findByProducts(all);
    }

    //get by region
    @Override
    public List<ProductResponse> findAllByRegion(Region region) {

         List<Product> all = productRepo.findByRegion(region);

        //get all productResponse
         return findByProducts(all);
    }

    //get by id
    @Override
    public ProductResponse findById(Long id) {
        Product product = productRepo.findById(id).orElseThrow(()-> new RuntimeException("Product not found"));
        return ProductResponse.builder()
                .title(product.getTitle())
                .address(product.getAddress())
                .likes(product.getLikes())
                .images(product.getImageUrl())
                .build();
    }

    @Override
    public SimpleRequest saveAll(ProductRequest[] newProduct) {
        ProductRequest productRequest = newProduct[0];

        productRepo.saveAll(productRequest.saveAll(newProduct));
          return SimpleRequest.builder()
                .message("Product saved")
                .status(HttpStatus.OK)
                .build();
    }

    @Override
    public PaginationResponse<ProductResponse> findAllWithPagination(int pageNumber, int sizePage) {
        Pageable pageable = PageRequest.of(pageNumber-1, sizePage);
        Page<Product> all = productRepo.findAll(pageable);
        var paginationResponse = new PaginationResponse<ProductResponse>();
        paginationResponse.setProducts(findByProducts(all.getContent()));
        paginationResponse.setPageNum(all.getNumber()+1);
        paginationResponse.setPageSize(all.getSize());
        paginationResponse.setNumberOfPages(all.getTotalPages());
        paginationResponse.setNumberOfElements(all.getTotalElements());
        return paginationResponse;
    }


    //get all productResponse method
    public List<ProductResponse> findByProducts(List<Product> productLike) {
        List<ProductResponse> responses = new ArrayList<>();
        for (Product product : productLike) {
            responses.add(
                    ProductResponse.builder()
                            .title(product.getTitle())
                            .address(product.getAddress())
                            .likes(product.getLikes())
                            .images(product.getImageUrl())
                            .build());
        }
        return responses;
    }
}
