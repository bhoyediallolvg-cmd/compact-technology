package gn.app.compacttechnology.controllers;

import gn.app.compacttechnology.models.dtos.ServiceDTO;
import gn.app.compacttechnology.services.ServiceEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/services")
@RequiredArgsConstructor
public class ServiceController {

    private final ServiceEntityService serviceEntityService;

    @GetMapping
    public ResponseEntity<List<ServiceDTO>> getAllServices() {
        return ResponseEntity.ok(serviceEntityService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServiceDTO> getServiceById(@PathVariable Integer id) {
        return serviceEntityService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/reference/{reference}")
    public ResponseEntity<ServiceDTO> getServiceByReference(@PathVariable String reference) {
        return serviceEntityService.findByReference(reference)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/enterprise/{enterpriseId}")
    public ResponseEntity<List<ServiceDTO>> getServicesByEnterpriseId(@PathVariable Integer enterpriseId) {
        return ResponseEntity.ok(serviceEntityService.findByEnterpriseId(enterpriseId));
    }

    @GetMapping("/search")
    public ResponseEntity<List<ServiceDTO>> getServicesByDescription(@RequestParam String description) {
        return ResponseEntity.ok(serviceEntityService.findByDescriptionContaining(description));
    }

    @GetMapping("/price/max/{maxPrice}")
    public ResponseEntity<List<ServiceDTO>> getServicesByMaxPrice(@PathVariable Integer maxPrice) {
        return ResponseEntity.ok(serviceEntityService.findByPriceLessThanEqual(maxPrice));
    }

    @GetMapping("/price/min/{minPrice}")
    public ResponseEntity<List<ServiceDTO>> getServicesByMinPrice(@PathVariable Integer minPrice) {
        return ResponseEntity.ok(serviceEntityService.findByPriceGreaterThanEqual(minPrice));
    }

    @PostMapping
    public ResponseEntity<ServiceDTO> createService(@RequestBody ServiceDTO serviceDTO) {
        try {
            ServiceDTO resultDTO = serviceEntityService.save(serviceDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(resultDTO);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ServiceDTO> updateService(@PathVariable Integer id, @RequestBody ServiceDTO serviceDTO) {
        if (!serviceEntityService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        try {
            ServiceDTO resultDTO = serviceEntityService.update(id, serviceDTO);
            return ResponseEntity.ok(resultDTO);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteService(@PathVariable Integer id) {
        if (!serviceEntityService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        serviceEntityService.delete(id);
        return ResponseEntity.noContent().build();
    }
}