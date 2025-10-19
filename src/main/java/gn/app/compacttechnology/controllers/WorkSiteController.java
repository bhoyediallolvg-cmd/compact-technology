package gn.app.compacttechnology.controllers;

import gn.app.compacttechnology.models.dtos.WorkSiteDTO;
import gn.app.compacttechnology.services.WorkSiteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/work-sites")
@RequiredArgsConstructor
public class WorkSiteController {

    private final WorkSiteService workSiteService;

    @GetMapping
    public ResponseEntity<List<WorkSiteDTO>> getAllWorkSites() {
        return ResponseEntity.ok(workSiteService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<WorkSiteDTO> getWorkSiteById(@PathVariable Integer id) {
        return workSiteService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/reference/{reference}")
    public ResponseEntity<WorkSiteDTO> getWorkSiteByReference(@PathVariable String reference) {
        return workSiteService.findByReference(reference)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<WorkSiteDTO>> getWorkSitesByCustomerId(@PathVariable Integer customerId) {
        return ResponseEntity.ok(workSiteService.findByCustomerId(customerId));
    }

    @GetMapping("/search/address")
    public ResponseEntity<List<WorkSiteDTO>> getWorkSitesByAddressContaining(@RequestParam String address) {
        return ResponseEntity.ok(workSiteService.findByAddressContaining(address));
    }

    @GetMapping("/search/reference")
    public ResponseEntity<List<WorkSiteDTO>> getWorkSitesByReferenceContaining(@RequestParam String reference) {
        return ResponseEntity.ok(workSiteService.findByReferenceContaining(reference));
    }

    @PostMapping
    public ResponseEntity<WorkSiteDTO> createWorkSite(@RequestBody WorkSiteDTO workSiteDTO) {
        try {
            WorkSiteDTO resultDTO = workSiteService.save(workSiteDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(resultDTO);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<WorkSiteDTO> updateWorkSite(@PathVariable Integer id, @RequestBody WorkSiteDTO workSiteDTO) {
        if (!workSiteService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        try {
            WorkSiteDTO resultDTO = workSiteService.update(id, workSiteDTO);
            return ResponseEntity.ok(resultDTO);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWorkSite(@PathVariable Integer id) {
        if (!workSiteService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        workSiteService.delete(id);
        return ResponseEntity.noContent().build();
    }
}