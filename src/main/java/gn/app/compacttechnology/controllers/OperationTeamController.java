package gn.app.compacttechnology.controllers;

import gn.app.compacttechnology.models.dtos.OperationTeamDTO;
import gn.app.compacttechnology.models.entities.OperationTeam.OperationTeamId;
import gn.app.compacttechnology.services.OperationTeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/operation-teams")
@RequiredArgsConstructor
public class OperationTeamController {

    private final OperationTeamService operationTeamService;

    @GetMapping
    public ResponseEntity<List<OperationTeamDTO>> getAllOperationTeams() {
        return ResponseEntity.ok(operationTeamService.findAll());
    }

    @GetMapping("/operation/{operationId}")
    public ResponseEntity<List<OperationTeamDTO>> getOperationTeamsByOperationId(@PathVariable Integer operationId) {
        return ResponseEntity.ok(operationTeamService.findByOperationId(operationId));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<OperationTeamDTO>> getOperationTeamsByUserId(@PathVariable Integer userId) {
        return ResponseEntity.ok(operationTeamService.findByUserId(userId));
    }

    @PostMapping
    public ResponseEntity<OperationTeamDTO> createOperationTeam(@RequestBody OperationTeamDTO operationTeamDTO) {
        try {
            OperationTeamDTO resultDTO = operationTeamService.save(operationTeamDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(resultDTO);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{operationId}/{userId}")
    public ResponseEntity<OperationTeamDTO> updateOperationTeam(
            @PathVariable Long operationId,
            @PathVariable Long userId,
            @RequestBody OperationTeamDTO operationTeamDTO) {
        
        OperationTeamId id = new OperationTeamId(operationId, userId);
        
        if (!operationTeamService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        
        try {
            OperationTeamDTO resultDTO = operationTeamService.update(id, operationTeamDTO);
            return ResponseEntity.ok(resultDTO);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{operationId}/{userId}")
    public ResponseEntity<Void> deleteOperationTeam(
            @PathVariable Long operationId,
            @PathVariable Long userId) {
        
        OperationTeamId id = new OperationTeamId(operationId, userId);
        
        if (!operationTeamService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        
        operationTeamService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/operation/{operationId}")
    public ResponseEntity<Void> deleteOperationTeamsByOperationId(@PathVariable Integer operationId) {
        operationTeamService.deleteByOperationId(operationId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/user/{userId}")
    public ResponseEntity<Void> deleteOperationTeamsByUserId(@PathVariable Integer userId) {
        operationTeamService.deleteByUserId(userId);
        return ResponseEntity.noContent().build();
    }
}