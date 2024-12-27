package tec.edu.azuay.sale.dto.responses;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class UserWithRoles implements Serializable {

    private UserDto user;

    private List<RoleDto> roles;
}
