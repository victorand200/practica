package tec.edu.azuay.sale.dto.responses;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class RoleDto implements Serializable {

    private Long roleId;

    private String role;

    private Boolean state;
}
