package gn.app.compacttechnology.controllers;

import gn.app.compacttechnology.models.dtos.ProductDTO;
import gn.app.compacttechnology.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        return ResponseEntity.ok(productService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable Integer id) {
        return productService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/reference/{reference}")
    public ResponseEntity<ProductDTO> getProductByReference(@PathVariable String reference) {
        return productService.findByReference(reference)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/enterprise/{enterpriseId}")
    public ResponseEntity<List<ProductDTO>> getProductsByEnterpriseId(@PathVariable Integer enterpriseId) {
        return ResponseEntity.ok(productService.findByEnterpriseId(enterpriseId));
    }

    @GetMapping("/type/{type}")
    public ResponseEntity<List<ProductDTO>> getProductsByType(@PathVariable Integer type) {
        return ResponseEntity.ok(productService.findByType(type));
    }

    @PostMapping
    public ResponseEntity<ProductDTO> createProduct(@RequestBody ProductDTO productDTO) {
        try {
            ProductDTO resultDTO = productService.save(productDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(resultDTO);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable Integer id, @RequestBody ProductDTO productDTO) {
        if (!productService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        try {
            ProductDTO resultDTO = productService.update(id, productDTO);
            return ResponseEntity.ok(resultDTO);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Integer id) {
        if (!productService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }
}