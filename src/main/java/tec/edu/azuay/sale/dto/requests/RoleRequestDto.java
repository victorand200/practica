package tec.edu.azuay.sale.dto.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class RoleRequestDto implements Serializable {

    @NotBlank(message = "Role is required.")
    private String role;

    @NotNull(message = "State is required.")
    private Boolean state;
}
