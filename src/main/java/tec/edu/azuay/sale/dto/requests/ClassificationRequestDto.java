package tec.edu.azuay.sale.dto.requests;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class ClassificationRequestDto implements Serializable {

    @NotBlank(message = "Group is required.")
    private String group;
}
