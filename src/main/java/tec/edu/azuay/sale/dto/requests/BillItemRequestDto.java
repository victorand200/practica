package tec.edu.azuay.sale.dto.requests;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class BillItemRequestDto implements Serializable {

    @NotNull(message = "Quantity is required")
    @Positive(message = "Quantity must be greater than 0")
    private Integer quantity;

    @NotNull(message = "Price is required")
    @PositiveOrZero(message = "Price must be greater than 0")
    private Double price;

    private Double subtotal;

    @NotNull(message = "Product id is required")
    @Positive(message = "Product id must be greater than 0")
    private Long productId;

    @NotNull(message = "Bill id is required")
    @Positive(message = "Bill id must be greater than 0")
    private Long billId;
}
