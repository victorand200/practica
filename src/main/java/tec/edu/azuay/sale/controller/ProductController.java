package tec.edu.azuay.sale.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tec.edu.azuay.sale.dto.requests.ProductRequestDto;
import tec.edu.azuay.sale.dto.responses.ProductDto;
import tec.edu.azuay.sale.service.interfaces.IGenericService;

import java.util.List;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final IGenericService<ProductDto, ProductRequestDto> productService;

    @Operation(summary = "Get product by id")
    @GetMapping
    public ResponseEntity<ProductDto> getProductById(@RequestParam Long id) {
        return ResponseEntity.ok(productService.getById(id));
    }

    @Operation(summary = "Get all products")
    @GetMapping("/all")
    public ResponseEntity<List<ProductDto>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllItems());
    }

    @Operation(summary = "Create a new product")
    @PostMapping
    public ResponseEntity<ProductDto> createProduct(@Valid @RequestBody ProductRequestDto productRequestDto) {
        return ResponseEntity.ok(productService.save(productRequestDto));
    }

    @Operation(summary = "Update a product")
    @PutMapping
    public ResponseEntity<ProductDto> updateProduct(@Valid @RequestBody ProductRequestDto requestDto, @RequestParam Long itemId) {
        return ResponseEntity.ok(productService.updateItem(requestDto, itemId));
    }

    @Operation(summary = "Delete a product")
    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProductById(Long itemId) {
        productService.deleteItem(itemId);
    }
}
