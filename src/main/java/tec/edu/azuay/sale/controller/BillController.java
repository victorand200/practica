package tec.edu.azuay.sale.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tec.edu.azuay.sale.dto.requests.BillRequestDto;
import tec.edu.azuay.sale.dto.responses.BillDto;
import tec.edu.azuay.sale.dto.responses.BillWithDetailsDto;
import tec.edu.azuay.sale.service.implement.BillServiceImpl;
import java.util.List;

@RestController
@RequestMapping("/bill")
@RequiredArgsConstructor
public class BillController {

    private final BillServiceImpl billService;

    @Operation(
            summary = "Get all bills",
            description = "Get all bills from the database"
    )
    @GetMapping("/all")
    public ResponseEntity<List<BillDto>> getAllBills() {
        return ResponseEntity.ok(billService.getAllItems());
    }

    @Operation(
            summary = "Get bill by id",
            description = "Insert a bill id to get the bill"
    )
    @GetMapping
    public ResponseEntity<BillDto> getBillById(@RequestParam Long billId) {
        return ResponseEntity.ok(billService.getById(billId));
    }

    @Operation(
            summary = "Get bill with details",
            description = "Insert a bill id to get the bill with its details(Products and quantities)"
    )
    @GetMapping("/details")
    public ResponseEntity<BillWithDetailsDto> getBillWithDetailsById(@RequestParam Long billId) {
        return ResponseEntity.ok(billService.getBillWithDetails(billId));
    }

    @Operation(
            summary = "Create a new bill",
            description = "Insert a bill to create a new bill"
    )
    @PostMapping
    public ResponseEntity<BillDto> createOneBill(@Valid @RequestBody BillRequestDto billRequestDto) {
        return ResponseEntity.ok(billService.save(billRequestDto));
    }

    @Operation(
            summary = "Update a bill",
            description = "Insert a bill to update a bill"
    )
    @PutMapping
    public ResponseEntity<BillDto> updateBill(@Valid @RequestBody BillRequestDto billRequestDto, @RequestParam Long billId) {
        return ResponseEntity.ok(billService.updateItem(billRequestDto, billId));
    }

    @Operation(
            summary = "Delete a bill",
            description = "Insert a bill id to delete the bill"
    )
    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBill(@RequestParam Long billId) {
        billService.deleteItem(billId);
    }
}
