package gn.app.compacttechnology.controllers;

import gn.app.compacttechnology.models.dtos.VehicleBreakdownDTO;
import gn.app.compacttechnology.models.entities.VehicleBreakdown.VehicleBreakdownId;
import gn.app.compacttechnology.services.VehicleBreakdownService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vehicle-breakdowns")
@RequiredArgsConstructor
public class VehicleBreakdownController {

    private final VehicleBreakdownService vehicleBreakdownService;

    @GetMapping
    public ResponseEntity<List<VehicleBreakdownDTO>> getAllVehicleBreakdowns() {
        return ResponseEntity.ok(vehicleBreakdownService.findAll());
    }

    @GetMapping("/vehicle/{vehicleId}")
    public ResponseEntity<List<VehicleBreakdownDTO>> getVehicleBreakdownsByVehicleId(@PathVariable Integer vehicleId) {
        return ResponseEntity.ok(vehicleBreakdownService.findByVehicleId(vehicleId));
    }

    @GetMapping("/breakdown/{breakdownId}")
    public ResponseEntity<List<VehicleBreakdownDTO>> getVehicleBreakdownsByBreakdownId(@PathVariable Integer breakdownId) {
        return ResponseEntity.ok(vehicleBreakdownService.findByBreakdownId(breakdownId));
    }

    @GetMapping("/operation/{operationId}")
    public ResponseEntity<List<VehicleBreakdownDTO>> getVehicleBreakdownsByOperationId(@PathVariable Integer operationId) {
        return ResponseEntity.ok(vehicleBreakdownService.findByOperationId(operationId));
    }

    @GetMapping("/no-operation")
    public ResponseEntity<List<VehicleBreakdownDTO>> getVehicleBreakdownsWithNoOperation() {
        return ResponseEntity.ok(vehicleBreakdownService.findByOperationIsNull());
    }

    @GetMapping("/vehicle/{vehicleId}/breakdown/{breakdownId}")
    public ResponseEntity<VehicleBreakdownDTO> getVehicleBreakdownByVehicleIdAndBreakdownId(
            @PathVariable Integer vehicleId,
            @PathVariable Integer breakdownId) {
        return vehicleBreakdownService.findByVehicleIdAndBreakdownId(vehicleId, breakdownId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<VehicleBreakdownDTO> createVehicleBreakdown(@RequestBody VehicleBreakdownDTO vehicleBreakdownDTO) {
        try {
            VehicleBreakdownDTO resultDTO = vehicleBreakdownService.save(vehicleBreakdownDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(resultDTO);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{vehicleId}/{breakdownId}")
    public ResponseEntity<VehicleBreakdownDTO> updateVehicleBreakdown(
            @PathVariable Long vehicleId,
            @PathVariable Long breakdownId,
            @RequestBody VehicleBreakdownDTO vehicleBreakdownDTO) {
        
        VehicleBreakdownId id = new VehicleBreakdownId(vehicleId, breakdownId);
        
        if (!vehicleBreakdownService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        
        try {
            VehicleBreakdownDTO resultDTO = vehicleBreakdownService.update(id, vehicleBreakdownDTO);
            return ResponseEntity.ok(resultDTO);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{vehicleId}/{breakdownId}")
    public ResponseEntity<Void> deleteVehicleBreakdown(
            @PathVariable Long vehicleId,
            @PathVariable Long breakdownId) {
        
        VehicleBreakdownId id = new VehicleBreakdownId(vehicleId, breakdownId);
        
        if (!vehicleBreakdownService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        
        vehicleBreakdownService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/vehicle/{vehicleId}")
    public ResponseEntity<Void> deleteVehicleBreakdownsByVehicleId(@PathVariable Integer vehicleId) {
        vehicleBreakdownService.deleteByVehicleId(vehicleId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/breakdown/{breakdownId}")
    public ResponseEntity<Void> deleteVehicleBreakdownsByBreakdownId(@PathVariable Integer breakdownId) {
        vehicleBreakdownService.deleteByBreakdownId(breakdownId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/operation/{operationId}")
    public ResponseEntity<Void> deleteVehicleBreakdownsByOperationId(@PathVariable Integer operationId) {
        vehicleBreakdownService.deleteByOperationId(operationId);
        return ResponseEntity.noContent().build();
    }
}