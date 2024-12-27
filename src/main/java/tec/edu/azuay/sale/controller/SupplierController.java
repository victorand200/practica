package tec.edu.azuay.sale.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tec.edu.azuay.sale.dto.requests.SupplierRequestDto;
import tec.edu.azuay.sale.dto.responses.SupplierDto;
import tec.edu.azuay.sale.service.interfaces.IGenericService;

import java.util.List;

@RestController
@RequestMapping("/supplier")
@RequiredArgsConstructor
public class SupplierController {

    private final IGenericService<SupplierDto, SupplierRequestDto> supplierService;

    @Operation(summary = "Get all suppliers")
    @GetMapping("/all")
    public ResponseEntity<List<SupplierDto>> getAllSuppliers() {
        return ResponseEntity.ok(supplierService.getAllItems());
    }

    @Operation(summary = "Get supplier by id")
    @GetMapping
    public ResponseEntity<SupplierDto> getSupplierById(@RequestParam Long itemId) {
        return ResponseEntity.ok(supplierService.getById(itemId));
    }

    @Operation(summary = "Create a new supplier, but the ruc must be unique")
    @PostMapping
    public ResponseEntity<SupplierDto> createOneSupplier(@Valid @RequestBody SupplierRequestDto supplierRequestDto) {
        return ResponseEntity.status(201).body(supplierService.save(supplierRequestDto));
    }

    @Operation(summary = "Update a supplier")
    @PutMapping
    public ResponseEntity<SupplierDto> updateSupplier(@RequestParam Long itemId, @Valid @RequestBody SupplierRequestDto supplierRequestDto) {
        return ResponseEntity.ok(supplierService.updateItem(supplierRequestDto, itemId));
    }

    @Operation(summary = "Delete a supplier")
    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSupplier(Long itemId) {
        supplierService.deleteItem(itemId);
    }
}
