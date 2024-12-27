package tec.edu.azuay.sale.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tec.edu.azuay.sale.dto.requests.BillItemRequestDto;
import tec.edu.azuay.sale.dto.responses.BillItemDto;
import tec.edu.azuay.sale.service.interfaces.IGenericService;

import java.util.List;

@RestController
@RequestMapping("/bill-item")
@RequiredArgsConstructor
public class BillItemController {

    private final IGenericService<BillItemDto, BillItemRequestDto> billItemService;

    @Operation(
            summary = "Get all bill items",
            description = "Get all bill items from the database"
    )
    @GetMapping("/all")
    public ResponseEntity<List<BillItemDto>> getAllBillItems() {
        return ResponseEntity.ok(billItemService.getAllItems());
    }

    @Operation(
            summary = "Get bill item by id",
            description = "Insert a bill item id to get the bill item"
    )
    @GetMapping
    public ResponseEntity<BillItemDto> getOneBillItemById(@RequestParam Long itemId) {
        return ResponseEntity.ok(billItemService.getById(itemId));
    }

    @Operation(
            summary = "Create a new bill item",
            description = "Insert a bill item to create a new bill item"
    )
    @PostMapping
    public ResponseEntity<BillItemDto> createOneBillItem(@Valid @RequestBody BillItemRequestDto billItemRequestDto) {
        return ResponseEntity.ok(billItemService.save(billItemRequestDto));
    }

    @Operation(
            summary = "Update a bill item",
            description = "Insert a bill item to update a bill item"
    )
    @PutMapping
    public ResponseEntity<BillItemDto> updateBillItem(@Valid @RequestBody BillItemRequestDto billItemRequestDto, @RequestParam Long itemId) {
        return ResponseEntity.ok(billItemService.updateItem(billItemRequestDto, itemId));
    }

    @Operation(
            summary = "Delete a bill item",
            description = "Insert a bill item id to delete a bill item"
    )
    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBillItem(@RequestParam Long itemId) {
        billItemService.deleteItem(itemId);
    }
}
