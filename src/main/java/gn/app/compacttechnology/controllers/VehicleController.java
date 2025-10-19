package gn.app.compacttechnology.controllers;

import gn.app.compacttechnology.models.dtos.VehicleDTO;
import gn.app.compacttechnology.services.VehicleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vehicles")
@RequiredArgsConstructor
public class VehicleController {

    private final VehicleService vehicleService;

    @GetMapping
    public ResponseEntity<List<VehicleDTO>> getAllVehicles() {
        return ResponseEntity.ok(vehicleService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<VehicleDTO> getVehicleById(@PathVariable Integer id) {
        return vehicleService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/registration/{registrationNumber}")
    public ResponseEntity<VehicleDTO> getVehicleByRegistrationNumber(@PathVariable String registrationNumber) {
        return vehicleService.findByRegistrationNumber(registrationNumber)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/enterprise/{enterpriseId}")
    public ResponseEntity<List<VehicleDTO>> getVehiclesByEnterpriseId(@PathVariable Integer enterpriseId) {
        return ResponseEntity.ok(vehicleService.findByEnterpriseId(enterpriseId));
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<VehicleDTO>> getVehiclesByStatus(@PathVariable Integer status) {
        return ResponseEntity.ok(vehicleService.findByStatus(status));
    }

    @GetMapping("/type/{type}")
    public ResponseEntity<List<VehicleDTO>> getVehiclesByType(@PathVariable Integer type) {
        return ResponseEntity.ok(vehicleService.findByType(type));
    }

    @PostMapping
    public ResponseEntity<VehicleDTO> createVehicle(@RequestBody VehicleDTO vehicleDTO) {
        try {
            VehicleDTO resultDTO = vehicleService.save(vehicleDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(resultDTO);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<VehicleDTO> updateVehicle(@PathVariable Integer id, @RequestBody VehicleDTO vehicleDTO) {
        if (!vehicleService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        try {
            VehicleDTO resultDTO = vehicleService.update(id, vehicleDTO);
            return ResponseEntity.ok(resultDTO);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVehicle(@PathVariable Integer id) {
        if (!vehicleService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        vehicleService.delete(id);
        return ResponseEntity.noContent().build();
    }
}