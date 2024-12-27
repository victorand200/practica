package tec.edu.azuay.sale.service.implement;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tec.edu.azuay.sale.dto.requests.RoleRequestDto;
import tec.edu.azuay.sale.dto.responses.RoleDto;
import tec.edu.azuay.sale.dto.responses.UserDto;
import tec.edu.azuay.sale.dto.responses.UserWithRoles;
import tec.edu.azuay.sale.exceptions.DuplicatedObjectException;
import tec.edu.azuay.sale.exceptions.ObjectNotFoundException;
import tec.edu.azuay.sale.persistence.models.Competition;
import tec.edu.azuay.sale.persistence.models.Role;
import tec.edu.azuay.sale.persistence.models.User;
import tec.edu.azuay.sale.persistence.repository.ICompetitionRepository;
import tec.edu.azuay.sale.persistence.repository.IRoleRepository;
import tec.edu.azuay.sale.persistence.repository.IUserRepository;
import tec.edu.azuay.sale.service.interfaces.IRoleService;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements IRoleService {

    private final IRoleRepository roleRepository;

    private final IUserRepository userRepository;

    private final ICompetitionRepository competitionRepository;

    private final ModelMapper modelMapper;

    private RoleDto entityToDto(Role entity) {
        return modelMapper.map(entity, RoleDto.class);
    }

    private Role dtoToEntity(RoleRequestDto request) {
        return modelMapper.map(request, Role.class);
    }

    @Override
    public RoleDto save(RoleRequestDto item) {
        return entityToDto(roleRepository.save(dtoToEntity(item)));
    }

    @Override
    public RoleDto getById(Long itemId) {
        return entityToDto(roleRepository.findById(itemId).orElseThrow(() -> new ObjectNotFoundException("Role with id %s not found".formatted(itemId))));
    }

    @Override
    public List<RoleDto> getAllItems() {
        return roleRepository.findAll().stream().map(this::entityToDto).toList();
    }

    @Override
    public RoleDto updateItem(RoleRequestDto item, Long itemId) {
        Role roleToUpdate = roleRepository.findById(itemId).orElseThrow(() -> new ObjectNotFoundException("Role with id %s not found".formatted(itemId)));

        roleToUpdate.setRole(Objects.nonNull(item.getRole()) ? item.getRole() : roleToUpdate.getRole());
        roleToUpdate.setState(Objects.nonNull(item.getState()) ? item.getState() : roleToUpdate.getState());

        return entityToDto(roleRepository.save(roleToUpdate));
    }

    @Override
    public void deleteItem(Long itemId) {
        roleRepository.findById(itemId).orElseThrow(() -> new ObjectNotFoundException("Role with id %s not found".formatted(itemId)));

        roleRepository.deleteById(itemId);
    }

    @Override
    @Transactional
    public String setRoleToUsers(List<Long> userId, Long roleId) {
        List<User> users = userRepository.findAllById(userId);

        List<Long> usersNotFound = users.
                stream()
                .map(User::getUserId)
                .filter(id -> !userId.contains(id))
                .toList();

        if (!usersNotFound.isEmpty()) {
            throw new ObjectNotFoundException("Users with id %s not found".formatted(usersNotFound));
        }

        Role role = roleRepository.findById(roleId).orElseThrow(() -> new ObjectNotFoundException("Role with id %s not found".formatted(roleId)));

        role.getUsers().removeIf(user -> userId.contains(user.getUserId()));

        role.getUsers().addAll(users);

        roleRepository.save(role);

        return "Role with id %s set to users with id %s".formatted(roleId, userId);
    }

    @Override
    @Transactional
    public String setRolesToUser(Long userId, List<Long> rolesId) {
        List<Role> roles = roleRepository.findAllById(rolesId);
        List<Long> rolesNotFound = rolesId.stream().filter(id -> !roles.stream().map(Role::getRoleId).toList().contains(id)).toList();

        if (!rolesNotFound.isEmpty()) {
            throw new ObjectNotFoundException("Roles with id %s not found".formatted(rolesNotFound));
        }

        List<Role> rolesWithOutUser = roles.stream().filter(role -> !role.getUsers().stream().map(User::getUserId).toList().contains(userId)).toList();

        User user = userRepository.findById(userId).orElseThrow(() -> new ObjectNotFoundException("User with id %s not found".formatted(userId)));

        if (roles.isEmpty()) {
            throw new ObjectNotFoundException("Roles with id %s not found".formatted(rolesId));
        }

        rolesWithOutUser.stream().peek(role -> role.getUsers().add(user)).forEach(roleRepository::save);

        return rolesWithOutUser.isEmpty() ? "User with id %s already has the specified roles".formatted(userId) : "Roles with id %s set to user with id %s".formatted(rolesWithOutUser.stream().map(Role::getRoleId).toList(), userId);
    }

    @Override
    public UserWithRoles getUserWithRoles(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ObjectNotFoundException("User with id %s not found".formatted(userId)));

        List<Role> roles = roleRepository.findAllByUserId(userId);

        if (roles.isEmpty()) {
            throw new ObjectNotFoundException("Roles not found for user with id %s".formatted(userId));
        }

        UserDto userDto = modelMapper.map(user, UserDto.class);

        UserWithRoles userWithRoles = new UserWithRoles();
        userWithRoles.setUser(userDto);
        userWithRoles.setRoles(roles.stream().map(this::entityToDto).toList());

        return userWithRoles;
    }

    @Override
    public String setRoleToCompetitions(Long idRole, Long idCompetition) {
        Role role = roleRepository.findById(idRole).orElseThrow(() -> new ObjectNotFoundException("Role with id %s not found".formatted(idRole)));
        Competition competition = competitionRepository.findById(idCompetition).orElseThrow(() -> new ObjectNotFoundException("Competition with id %s not found".formatted(idCompetition)));

        role.getCompetitions().removeIf(comp -> comp.getCompetitionId().equals(idCompetition));

        role.getCompetitions().add(competition);

        return "Role with id %s set to competition with id %s".formatted(idRole, idCompetition);
    }

}
