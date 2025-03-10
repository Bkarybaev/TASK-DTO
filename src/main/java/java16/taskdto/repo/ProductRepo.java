package java16.taskdto.repo;

import java16.taskdto.entityes.Product;
import java16.taskdto.enums.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepo extends JpaRepository<Product, Long> {
    @Query("select p from Product p where p.region = :region")
    List<Product> findByRegion(Region region);
}
