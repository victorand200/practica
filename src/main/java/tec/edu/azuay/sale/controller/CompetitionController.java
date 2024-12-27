package tec.edu.azuay.sale.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tec.edu.azuay.sale.dto.requests.CompetitionRequestDto;
import tec.edu.azuay.sale.dto.responses.CompetitionDto;
import tec.edu.azuay.sale.service.interfaces.IGenericService;

import java.util.List;

@RestController
@RequestMapping("/competition")
@RequiredArgsConstructor
public class CompetitionController {

    private final IGenericService<CompetitionDto, CompetitionRequestDto> competitionService;

    @Operation(summary = "Get competition by id")
    @GetMapping
    public ResponseEntity<CompetitionDto> getCompetitionById(@RequestParam Long id) {
        return ResponseEntity.ok(competitionService.getById(id));
    }

    @Operation(summary = "Get all competitions")
    @GetMapping("/all")
    public ResponseEntity<List<CompetitionDto>> getAllCompetitions() {
        return ResponseEntity.ok(competitionService.getAllItems());
    }

    @Operation(summary = "Create a new competition")
    @PostMapping
    public ResponseEntity<CompetitionDto> createCompetition(@Valid @RequestBody CompetitionRequestDto competitionRequestDto) {
        return ResponseEntity.ok(competitionService.save(competitionRequestDto));
    }

    @Operation(summary = "Update a competition")
    @PutMapping
    public ResponseEntity<CompetitionDto> updateCompetition(@RequestBody Long id, @Valid @RequestBody CompetitionRequestDto competitionRequestDto) {
        return ResponseEntity.ok(competitionService.updateItem(competitionRequestDto, id));
    }

    @Operation(summary = "Delete a competition")
    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCompetition(Long id) {
        competitionService.deleteItem(id);
    }
}
