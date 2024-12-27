package tec.edu.azuay.sale.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tec.edu.azuay.sale.dto.requests.ClassificationRequestDto;
import tec.edu.azuay.sale.dto.responses.ClassificationDto;
import tec.edu.azuay.sale.service.interfaces.IGenericService;

import java.util.List;

@RestController
@RequestMapping("/classification")
@RequiredArgsConstructor
public class ClassificationController {

    private final IGenericService<ClassificationDto, ClassificationRequestDto> classificationService;

    @Operation(
            summary = "Get classification by id",
            description = "Get classification by id"
    )
    @GetMapping
    public ResponseEntity<ClassificationDto> getClassificationById(@RequestParam Long id) {
        return ResponseEntity.ok(classificationService.getById(id));
    }

    @Operation(
            summary = "Get all classifications",
            description = "Get all classifications"
    )
    @GetMapping("/all")
    public ResponseEntity<List<ClassificationDto>> getAllClassifications() {
        return ResponseEntity.ok(classificationService.getAllItems());
    }

    @Operation(
            summary = "Create classification",
            description = "Create classification"
    )
    @PostMapping
    public ResponseEntity<ClassificationDto> createClassification(@Valid @RequestBody ClassificationRequestDto classificationRequestDto) {
        return ResponseEntity.ok(classificationService.save(classificationRequestDto));
    }

    @Operation(
            summary = "Update classification",
            description = "Update classification"
    )
    @PutMapping
    public ResponseEntity<ClassificationDto> updateClassification(@RequestParam Long id, @Valid @RequestBody ClassificationRequestDto classificationRequestDto) {
        return ResponseEntity.ok(classificationService.updateItem(classificationRequestDto, id));
    }

    @Operation(
            summary = "Delete classification",
            description = "Delete classification"
    )
    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteClassification(Long id) {
        classificationService.deleteItem(id);
    }
}
