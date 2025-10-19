package gn.app.compacttechnology.controllers;

import gn.app.compacttechnology.models.dtos.OperationDTO;
import gn.app.compacttechnology.services.OperationService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/operations")
@RequiredArgsConstructor
public class OperationController {

    private final OperationService operationService;

    @GetMapping
    public ResponseEntity<List<OperationDTO>> getAllOperations() {
        return ResponseEntity.ok(operationService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OperationDTO> getOperationById(@PathVariable Integer id) {
        return operationService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/reference/{reference}")
    public ResponseEntity<OperationDTO> getOperationByReference(@PathVariable String reference) {
        return operationService.findByReference(reference)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/start-between")
    public ResponseEntity<List<OperationDTO>> getOperationsByStartAtBetween(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end) {
        return ResponseEntity.ok(operationService.findByStartAtBetween(start, end));
    }

    @GetMapping("/end-between")
    public ResponseEntity<List<OperationDTO>> getOperationsByEndAtBetween(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end) {
        return ResponseEntity.ok(operationService.findByEndAtBetween(start, end));
    }

    @GetMapping("/started-between")
    public ResponseEntity<List<OperationDTO>> getOperationsByStartedAtBetween(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end) {
        return ResponseEntity.ok(operationService.findByStartedAtBetween(start, end));
    }

    @GetMapping("/finished-between")
    public ResponseEntity<List<OperationDTO>> getOperationsByFinishedAtBetween(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end) {
        return ResponseEntity.ok(operationService.findByFinishedAtBetween(start, end));
    }

    @GetMapping("/work-site/{workSiteId}")
    public ResponseEntity<List<OperationDTO>> getOperationsByWorkSiteId(@PathVariable Integer workSiteId) {
        return ResponseEntity.ok(operationService.findByWorkSiteId(workSiteId));
    }

    @GetMapping("/service/{serviceId}")
    public ResponseEntity<List<OperationDTO>> getOperationsByServiceId(@PathVariable Integer serviceId) {
        return ResponseEntity.ok(operationService.findByServiceId(serviceId));
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<OperationDTO>> getOperationsByCustomerId(@PathVariable Integer customerId) {
        return ResponseEntity.ok(operationService.findByCustomerId(customerId));
    }

    @GetMapping("/vehicle/{vehicleId}")
    public ResponseEntity<List<OperationDTO>> getOperationsByVehicleId(@PathVariable Integer vehicleId) {
        return ResponseEntity.ok(operationService.findByVehicleId(vehicleId));
    }

    @GetMapping("/team-lead/{teamLeadId}")
    public ResponseEntity<List<OperationDTO>> getOperationsByTeamLeadId(@PathVariable Integer teamLeadId) {
        return ResponseEntity.ok(operationService.findByTeamLeadId(teamLeadId));
    }

    @GetMapping("/not-started")
    public ResponseEntity<List<OperationDTO>> getOperationsNotStarted() {
        return ResponseEntity.ok(operationService.findByStartedAtIsNull());
    }

    @GetMapping("/not-finished")
    public ResponseEntity<List<OperationDTO>> getOperationsNotFinished() {
        return ResponseEntity.ok(operationService.findByFinishedAtIsNull());
    }

    @PostMapping
    public ResponseEntity<OperationDTO> createOperation(@RequestBody OperationDTO operationDTO) {
        try {
            OperationDTO resultDTO = operationService.save(operationDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(resultDTO);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<OperationDTO> updateOperation(@PathVariable Integer id, @RequestBody OperationDTO operationDTO) {
        if (!operationService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        try {
            OperationDTO resultDTO = operationService.update(id, operationDTO);
            return ResponseEntity.ok(resultDTO);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOperation(@PathVariable Integer id) {
        if (!operationService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        operationService.delete(id);
        return ResponseEntity.noContent().build();
    }
}