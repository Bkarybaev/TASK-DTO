package java16.taskdto.request;

import java16.taskdto.entityes.Product;
import java16.taskdto.enums.Region;
import java16.taskdto.enums.RoleProduct;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ProductRequest {

    private String title;

    private BigDecimal price;

    private RoleProduct roleProduct;

    private int maxOfGuests;

    private String description;

    private Region region;

    private List<String> imageUrl = new ArrayList<>();

    private String address;


    public Product save() {
        Product product = new Product();
        product.setTitle(title);
        product.setPrice(price);
        product.setRoleProduct(roleProduct);
        product.setMaxOfGuests(maxOfGuests);
        product.setDescription(description);
        product.setRegion(region);
        product.setImageUrl(imageUrl);
        product.setAddress(address);
        return product;
    }

    public List<Product> saveAll(ProductRequest[] newProduct) {
        List<Product> products = new ArrayList<>();
        for (ProductRequest productRequest : newProduct) {
            products.add(productRequest.save());
        }
        return products;
    }

}
