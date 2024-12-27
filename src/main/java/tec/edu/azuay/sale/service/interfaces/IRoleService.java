package tec.edu.azuay.sale.service.interfaces;

import tec.edu.azuay.sale.dto.requests.RoleRequestDto;
import tec.edu.azuay.sale.dto.responses.RoleDto;
import tec.edu.azuay.sale.dto.responses.UserWithRoles;

import java.util.List;

public interface IRoleService extends IGenericService<RoleDto, RoleRequestDto> {

    String setRoleToUsers(List<Long> userId, Long roleId);

    String setRolesToUser(Long userId, List<Long> rolesId);

    UserWithRoles getUserWithRoles(Long userId);

    String setRoleToCompetitions(Long idRole, Long idCompetition);
}
