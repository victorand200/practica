package tec.edu.azuay.sale.persistence.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(onlyExplicitlyIncluded = true)
@Entity
public class Classification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long classificationId;

    @Column(name = "group_name")
    private String group;

    @OneToMany(targetEntity = Product.class, fetch = FetchType.LAZY, mappedBy = "classification")
    private List<Product> products = new ArrayList<>();
}
