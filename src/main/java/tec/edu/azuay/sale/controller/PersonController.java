package tec.edu.azuay.sale.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tec.edu.azuay.sale.dto.requests.PersonRequestDto;
import tec.edu.azuay.sale.dto.responses.PersonDto;
import tec.edu.azuay.sale.service.interfaces.IPersonService;

import java.util.List;

@RestController
@RequestMapping("/person")
@RequiredArgsConstructor
public class PersonController {

    private final IPersonService personService;

    @Operation(summary = "Get all people")
    @GetMapping("/all")
    public ResponseEntity<List<PersonDto>> getAllPeople() {
        return ResponseEntity.ok(personService.getAllItems());
    }

    @Operation(summary = "Get person by dni")
    @GetMapping("/dni")
    public ResponseEntity<PersonDto> getPersonByDni(@RequestParam String dni) {
        return ResponseEntity.ok(personService.getUserByDni(dni));
    }

    @Operation(summary = "Get person full name", description = "Get the full name of a person by dni")
    @GetMapping("/name")
    public ResponseEntity<String> getPersonFullName(@RequestParam String dni) {
        return ResponseEntity.ok(personService.getFullName(dni));
    }

    @Operation(summary = "Get person by id")
    @GetMapping
    public ResponseEntity<PersonDto> getPersonById(@RequestParam Long id) {
        return ResponseEntity.ok(personService.getById(id));
    }

    @Operation(summary = "Create a new person", description = "Create a new person with the given data, but the dni must be unique")
    @PostMapping
    public ResponseEntity<PersonDto> savePerson(@Valid @RequestBody PersonRequestDto personRequestDto) {
        return ResponseEntity.ok(personService.save(personRequestDto));
    }

    @Operation(summary = "Update a person")
    @PutMapping
    public ResponseEntity<PersonDto> updatePerson(@RequestParam Long id, @Valid @RequestBody PersonRequestDto personRequestDto) {
        return ResponseEntity.ok(personService.updateItem(personRequestDto, id));
    }

    @Operation(summary = "Delete a person")
    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePerson(Long id) {
        personService.deleteItem(id);
    }
}
