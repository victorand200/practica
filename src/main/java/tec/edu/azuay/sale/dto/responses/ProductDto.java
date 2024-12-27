package tec.edu.azuay.sale.dto.responses;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class ProductDto implements Serializable {

    private Long productId;

    private Integer stock;

    private Double unitPrice;

    private String unit;

    private Boolean iva;

    private ClassificationDto classification;

    private SupplierDto supplier;
}
