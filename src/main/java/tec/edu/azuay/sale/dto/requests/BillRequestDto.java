package tec.edu.azuay.sale.dto.requests;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
public class BillRequestDto implements Serializable {

    @NotBlank(message = "Ruc is required")
    @Pattern(regexp = "^[0-9]{13}$", message = "Ruc must have 13 digits")
    private String ruc;

    @NotNull(message = "Discount is required")
    private Double discount;

    @NotNull(message = "Date is required")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime date;

    @NotNull(message = "Total is required")
    @DecimalMax(value = "999999999.99", message = "Total must be less than 999999999.99")
    private Double total;

    @NotNull(message = "The field is required, please provide a existing value's person")
    private Long personId;

    @NotNull(message = "The field is required, please provide a existing value's pay method")
    private Long payMethodId;
}
