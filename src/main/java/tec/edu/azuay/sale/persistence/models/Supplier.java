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
public class Supplier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long supplierId;

    @Column(unique = true)
    private String ruc;

    private String phone;

    private String country;

    private String email;

    private String currency;

    @OneToMany(targetEntity = Product.class, fetch = FetchType.LAZY, mappedBy = "supplier")
    private List<Product> products = new ArrayList<>();
}
