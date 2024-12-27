package tec.edu.azuay.sale.dto.requests;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class UserRequestDto implements Serializable {

    @NotBlank(message = "Username is required")
    private String user;

    @NotBlank(message = "Password is required")
    private String password;

    @NotNull(message = "The field is required, please provide a existing value's person")
    @Positive(message = "The field is required, please provide a existing value's person")
    @Min(value = 1, message = "The field is required, please provide a existing value's person")
    private Long personId;
}
