package tec.edu.azuay.sale.dto.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class SupplierRequestDto implements Serializable {

    @NotBlank(message = "Ruc is required.")
    @Pattern(regexp = "^[0-9]{13}$", message = "Ruc must have 13 digits.")
    private String ruc;

    @NotBlank(message = "Phone is required.")
    @Pattern(regexp = "^[0-9]{10}$", message = "Phone must have 10 digits.")
    private String phone;

    @NotBlank(message = "Country is required.")
    @Pattern(regexp = "^[A-Za-z ]+$", message = "Country must have only letters.")
    private String country;

    @NotBlank(message = "Email is required.")
    @Email(message = "Email is invalid.", regexp = "^[A-Za-z0-9+_.-]+@(.+)$")
    private String email;

    @NotBlank(message = "Currency is required.")
    private String currency;
}
