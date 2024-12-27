package tec.edu.azuay.sale.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tec.edu.azuay.sale.dto.requests.PayMethodRequestDto;
import tec.edu.azuay.sale.dto.responses.PayMethodDto;
import tec.edu.azuay.sale.service.interfaces.IGenericService;

import java.util.List;

@RestController
@RequestMapping("/pay-method")
@RequiredArgsConstructor
public class PayMethodController {

    private final IGenericService<PayMethodDto, PayMethodRequestDto> payMethodService;

    @Operation(summary = "Get pay method by id")
    @GetMapping
    public ResponseEntity<PayMethodDto> getPayMethodById(@RequestParam Long id) {
        return ResponseEntity.ok(payMethodService.getById(id));
    }

    @Operation(summary = "Get all pay methods")
    @GetMapping("/all")
    public ResponseEntity<List<PayMethodDto>> getAllPayMethods() {
        return ResponseEntity.ok(payMethodService.getAllItems());
    }

    @Operation(summary = "Create a new pay method")
    @PostMapping
    public ResponseEntity<PayMethodDto> createPayMethod(@Valid @RequestBody PayMethodRequestDto payMethodRequestDto) {
        return ResponseEntity.ok(payMethodService.save(payMethodRequestDto));
    }

    @Operation(summary = "Update a pay method")
    @PutMapping
    public ResponseEntity<PayMethodDto> updatePayMethod(@RequestParam Long id, @Valid @RequestBody PayMethodRequestDto payMethodRequestDto) {
        return ResponseEntity.ok(payMethodService.updateItem(payMethodRequestDto, id));
    }

    @Operation(summary = "Delete a pay method")
    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePayMethod(Long id) {
        payMethodService.deleteItem(id);
    }
}
