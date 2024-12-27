package tec.edu.azuay.sale.dto.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class ProductRequestDto implements Serializable {

    @NotNull(message = "Stock is required.")
    @Positive(message = "Stock must be a positive number.")
    private Integer stock;

    @NotNull(message = "Unit price is required.")
    @Positive(message = "Unit price must be a positive number.")
    private Double unitPrice;

    @NotBlank(message = "Name is required.")
    private String unit;

    @NotNull(message = "Iva is required.")
    private Boolean iva;

    @NotNull(message = "The field is required, please provide a existing value's classification.")
    private Long classificationId;

    @NotNull(message = "The field is required, please provide a existing value's supplier.")
    private Long supplierId;
}
