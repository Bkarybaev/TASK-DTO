package java16.taskdto.repo;

import java16.taskdto.entityes.Product;
import java16.taskdto.enums.Region;
import java16.taskdto.exceptions.ProductNotFount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepo extends JpaRepository<Product, Long> {
    @Query("select p from Product p where p.region = :region")
    List<Product> findByRegion(Region region);

    default Product findByIdException(Long id){
     return findById(id).orElseThrow(()
             -> new ProductNotFount(String.format("Product with id %s not found", id)));
    }
}
