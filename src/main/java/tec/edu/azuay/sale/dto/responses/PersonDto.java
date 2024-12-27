package tec.edu.azuay.sale.dto.responses;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class PersonDto implements Serializable {

    private Long personId;

    private String name;

    private String lastName;

    private String dni;

    private String phone;

    private String email;
}
