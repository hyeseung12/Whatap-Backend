package io.whatap.whatap.domain.product;

import io.whatap.whatap.domain.product.dto.AddProductRequest;
import io.whatap.whatap.domain.product.dto.ProductResponse;
import io.whatap.whatap.domain.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/product")
public class ProductController {
    private final ProductService productService;

    @PostMapping
    public ResponseEntity<ProductResponse> addProduct(@RequestBody @Valid AddProductRequest request) {
        ProductResponse product = productService.save(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(product);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProduct(@PathVariable Long id) {
        ProductResponse product = productService.findById(id);

        return ResponseEntity.ok()
                .body(product);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.delete(id);
        return ResponseEntity.ok()
                .build();
    }
}
