package java16.taskdto.entityes;

import jakarta.persistence.*;
import java16.taskdto.enums.Region;
import java16.taskdto.enums.RoleProduct;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "gen_product_id")
    @SequenceGenerator(name = "gen_product_id",sequenceName = "seq_product_id",allocationSize = 1,initialValue = 100)
    private Long id;

    private String title;

    private BigDecimal price;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", length = 50)
    private RoleProduct roleProduct;

    private int maxOfGuests;

    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "region", length = 100)
    private Region region;

    @ElementCollection
    @Column(name = "image_url", length = 1000)
    private List<String> imageUrl = new ArrayList<>();

    @Column(name = "address")
    private String address;

    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<Like> likes = new ArrayList<>();
}
