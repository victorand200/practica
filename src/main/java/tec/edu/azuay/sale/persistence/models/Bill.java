package tec.edu.azuay.sale.persistence.models;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(onlyExplicitlyIncluded = true)
@Entity
public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long billId;

    @Column(unique = true)
    private String ruc;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime date;

    private Double discount;

    private Double total;

    @ManyToOne(targetEntity = Person.class)
    @JoinColumn(name = "person_id")
    private Person person;

    @ManyToOne(targetEntity = PayMethod.class)
    @JoinColumn(name = "pay_method_id")
    private PayMethod payMethod;

    @OneToMany(targetEntity = BillItem.class, fetch = FetchType.LAZY, mappedBy = "bill")
    private List<BillItem> billItems;
}
