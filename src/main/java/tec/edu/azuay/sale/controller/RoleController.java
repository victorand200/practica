package tec.edu.azuay.sale.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tec.edu.azuay.sale.dto.requests.RoleRequestDto;
import tec.edu.azuay.sale.dto.responses.RoleDto;
import tec.edu.azuay.sale.dto.responses.UserWithRoles;
import tec.edu.azuay.sale.service.interfaces.IRoleService;

import java.util.List;

@RestController
@RequestMapping("/role")
@RequiredArgsConstructor
public class RoleController {

    private final IRoleService roleService;

    @Operation(
            summary = "Get all roles",
            description = "Get all roles from the database"
    )
    @GetMapping("/all")
    public ResponseEntity<List<RoleDto>> getAllRole() {
        return ResponseEntity.ok(roleService.getAllItems());
    }

    @Operation(
            summary = "Get role by id",
            description = "Insert a role id to get the role"
    )
    @GetMapping
    public ResponseEntity<RoleDto> getRoleById(@RequestParam Long id) {
        return ResponseEntity.ok(roleService.getById(id));
    }

    @Operation(
            summary = "Get user with roles",
            description = "Insert a user id to get the user with his assigned roles"
    )
    @GetMapping("/user")
    public ResponseEntity<UserWithRoles> getUserWithRoles(Long idUser) {
        return ResponseEntity.ok(roleService.getUserWithRoles(idUser));
    }

    @Operation(
            summary = "Create a new role",
            description = "Insert a role to create a new role"
    )
    @PostMapping
    public ResponseEntity<RoleDto> createOneRole(@Valid @RequestBody RoleRequestDto roleRequestDto) {
        return ResponseEntity.ok(roleService.save(roleRequestDto));
    }

    @Operation(
            summary = "Set role to users",
            description = "Insert a list of user ids and a role id to assign the role to the users"
    )
    @PostMapping("/set-role-to-users")
    public ResponseEntity<String> setRoleToUsers(@RequestParam List<Long> userId, @RequestParam Long roleId) {
        return ResponseEntity.ok(roleService.setRoleToUsers(userId, roleId));
    }

    @Operation(
            summary = "Set roles to user",
            description = "Insert a user id and a list of role ids to assign the roles to the user"
    )
    @PostMapping("/set-roles-to-user")
    public ResponseEntity<String> setRolesToUser(@RequestParam Long userId, @RequestParam List<Long> rolesId) {
        String response = roleService.setRolesToUser(userId, rolesId);
        int statusCode = response.contains("already has") ? HttpStatus.CONFLICT.value() : HttpStatus.OK.value();

        return ResponseEntity.status(statusCode).body(response);
    }

    @Operation(
            summary = "Set role to competition",
            description = "Insert a role id and a competition id to assign the role to the competition"
    )
    @PostMapping("/set-role-to-competition")
    public ResponseEntity<String> setRoleToCompetitions(@RequestParam Long idRole, @RequestParam Long idCompetition) {
        return ResponseEntity.ok(roleService.setRoleToCompetitions(idRole, idCompetition));
    }

    @Operation(
            summary = "Update a role",
            description = "Insert a role id and a role(\"Name of the role\") to update the role"
    )
    @PutMapping
    public ResponseEntity<RoleDto> updateRole(@RequestParam Long id, @Valid @RequestBody RoleRequestDto roleRequestDto) {
        return ResponseEntity.ok(roleService.updateItem(roleRequestDto, id));
    }

    @Operation(
            summary = "Delete a role",
            description = "Insert a role id to delete the role"
    )
    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteRole(@RequestParam Long id) {
        roleService.deleteItem(id);
    }
}
