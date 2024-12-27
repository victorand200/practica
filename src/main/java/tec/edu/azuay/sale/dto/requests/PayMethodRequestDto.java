package tec.edu.azuay.sale.dto.requests;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class PayMethodRequestDto implements Serializable {

    @NotBlank(message = "Type is required.")
    private String type;

    @NotBlank(message = "Description is required.")
    private String description;
}
