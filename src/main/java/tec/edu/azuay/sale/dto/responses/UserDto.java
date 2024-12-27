package tec.edu.azuay.sale.dto.responses;


import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class UserDto implements Serializable {

    @NotNull(message = "UserId is required")
    @Positive(message = "UserId must be positive")
    @Min(value = 1, message = "UserId must be greater than 0")
    private Long userId;

    @NotBlank(message = "Username is required")
    private String user;

    @NotBlank(message = "Password is required")
    private String password;

    @Valid
    private PersonDto person;
}
