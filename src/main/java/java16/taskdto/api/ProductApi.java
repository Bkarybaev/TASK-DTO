package java16.taskdto.api;
import jakarta.annotation.security.PermitAll;
import java16.taskdto.enums.Region;
import java16.taskdto.request.ProductRequest;
import java16.taskdto.request.SimpleRequest;
import java16.taskdto.response.PaginationResponse;
import java16.taskdto.response.ProductResponse;
import java16.taskdto.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/product")
public class ProductApi {
    private final ProductService productService;

    //save product
    @PreAuthorize("hasAuthority('ROLE_OWNER')")
    @PostMapping("/save_product")
    public SimpleRequest saveProduct(@RequestBody ProductRequest newProduct) {
        return productService.save(newProduct);
    }
//    @PreAuthorize("hasAuthority('OWNER')")
    @PostMapping("/save_AllProduct")
    public SimpleRequest saveAllProduct(@RequestBody ProductRequest[] newProduct) {
        return productService.saveAll(newProduct);
    }

    //get all products
    @GetMapping("/getAll")
    public List<ProductResponse> getAll() {
        return productService.findAll();
    }

    //get by region
    @PreAuthorize("hasAuthority('ROLE_OWNER')")
    @GetMapping("/getByRegion")
    public ResponseEntity<List<ProductResponse>> getByRegion(@RequestParam Region region) {
       return ResponseEntity.ok(productService.findAllByRegion(region));
    }

    //get by id
    @GetMapping("/getById/{id}")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_OWNER')")
    public ProductResponse getById(@PathVariable Long id) {
        return productService.findById(id);
    }

    //get all pagination
    @GetMapping("/getAllWithPagination")
    @PermitAll
    public PaginationResponse<ProductResponse> getAllProducts(
            @RequestParam(defaultValue = "1") int pageNumber,
            @RequestParam(defaultValue = "10")int sizePage
    ) {
        return productService.findAllWithPagination(pageNumber,sizePage);
    }



}
