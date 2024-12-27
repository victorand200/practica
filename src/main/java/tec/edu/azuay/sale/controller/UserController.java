package tec.edu.azuay.sale.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tec.edu.azuay.sale.dto.requests.UserRequestDto;
import tec.edu.azuay.sale.dto.responses.UserDto;
import tec.edu.azuay.sale.service.implement.UserServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserServiceImpl userService;

    @Operation(
            summary = "Get all users",
            description = "Get all users from the database"
    )
    @GetMapping("/all")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllItems());
    }

    @Operation(
            summary = "Get user by id",
            description = "Insert a user id to get the user"
    )
    @GetMapping
    public ResponseEntity<UserDto> getOneUser(@RequestParam Long userId) {
        return ResponseEntity.ok(userService.getById(userId));
    }

    @Operation(
            summary = "Get user by username",
            description = "Insert a username to get the user"
    )
    @GetMapping("/{user}")
    public ResponseEntity<UserDto> getOneUserByUser(@PathVariable String user) {
        return ResponseEntity.ok(userService.findOneByUser(user));
    }

    @Operation(
            summary = "Create a new user",
            description = "Insert a user to create a new user, but the username must be unique"
    )
    @PostMapping
    public ResponseEntity<UserDto> saveUser(@Valid @RequestBody UserRequestDto userDto) {
        return ResponseEntity.ok(userService.save(userDto));
    }

    @Operation(
            summary = "Update user",
            description = """
                    Important: The username cannot be updated.
                    If the attribute is null, it will not be updated.
                    If the user does not exist, it will throw an exception.
                    If need update the attribute's person, use the person controller.
                    To update the person reference here, send only the person ID.
                   """
    )
    @PutMapping
    public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserRequestDto userDto, @RequestParam Long userId) {
        return ResponseEntity.ok(userService.updateItem(userDto, userId));
    }

    @Operation(
            summary = "Delete user",
            description = "Insert a user id to delete the user"
    )
    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@RequestParam Long userId) {
        userService.deleteItem(userId);
    }
}
