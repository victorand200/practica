package tec.edu.azuay.sale.persistence.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(onlyExplicitlyIncluded = true)
@Entity
public class PayMethod {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long payMethodId;

    @Column(nullable = false, unique = true)
    private String type;

    private String description;

    @OneToMany(targetEntity = Bill.class, fetch = FetchType.LAZY, mappedBy = "payMethod")
    private List<Bill> bills;
}
