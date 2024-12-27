package tec.edu.azuay.sale.persistence.models;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(onlyExplicitlyIncluded = true)
@Entity
public class BillItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long billItemId;

    private Integer quantity;

    private Double price;

    private Double subtotal;

    @ManyToOne(targetEntity = Product.class)
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne(targetEntity = Bill.class)
    @JoinColumn(name = "bill_id")
    private Bill bill;

    public void setSubtotal() {
        this.subtotal = this.quantity * this.price;
    }
}
