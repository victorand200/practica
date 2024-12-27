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
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    private Integer stock;

    private Double unitPrice;

    private String unit;

    private Boolean iva;

    @ManyToOne(targetEntity = Classification.class)
    @JoinColumn(name = "classification_id")
    private Classification classification;

    @ManyToOne(targetEntity = Supplier.class)
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;

    @OneToMany(targetEntity = BillItem.class, fetch = FetchType.LAZY, mappedBy = "product")
    private List<BillItem> billItem = new ArrayList<>();
}
