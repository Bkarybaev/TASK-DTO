package java16.taskdto.entityes;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DialectOverride;

@Entity
@Table(name = "likes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Like {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen_like_id")
    @SequenceGenerator(
            name = "gen_like_id",
            sequenceName = "seq_like_id",
            allocationSize = 1,
            initialValue = 100)
    private Long id;
    private Boolean liked;
    @ManyToOne
    private User user;
    @ManyToOne
    private Product product;
}
