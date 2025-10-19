package gn.app.compacttechnology.controllers;

import gn.app.compacttechnology.models.dtos.OperationExpenseDTO;
import gn.app.compacttechnology.models.entities.OperationExpense.OperationExpenseId;
import gn.app.compacttechnology.services.OperationExpenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/operation-expenses")
@RequiredArgsConstructor
public class OperationExpenseController {

    private final OperationExpenseService operationExpenseService;

    @GetMapping
    public ResponseEntity<List<OperationExpenseDTO>> getAllOperationExpenses() {
        return ResponseEntity.ok(operationExpenseService.findAll());
    }

    @GetMapping("/operation/{operationId}")
    public ResponseEntity<List<OperationExpenseDTO>> getOperationExpensesByOperationId(@PathVariable Integer operationId) {
        return ResponseEntity.ok(operationExpenseService.findByOperationId(operationId));
    }

    @GetMapping("/expense/{expenseId}")
    public ResponseEntity<List<OperationExpenseDTO>> getOperationExpensesByExpenseId(@PathVariable Integer expenseId) {
        return ResponseEntity.ok(operationExpenseService.findByExpenseId(expenseId));
    }

    @GetMapping("/cost/min/{minCost}")
    public ResponseEntity<List<OperationExpenseDTO>> getOperationExpensesByMinCost(@PathVariable Integer minCost) {
        return ResponseEntity.ok(operationExpenseService.findByCostGreaterThanEqual(minCost));
    }

    @GetMapping("/cost/max/{maxCost}")
    public ResponseEntity<List<OperationExpenseDTO>> getOperationExpensesByMaxCost(@PathVariable Integer maxCost) {
        return ResponseEntity.ok(operationExpenseService.findByCostLessThanEqual(maxCost));
    }

    @PostMapping
    public ResponseEntity<OperationExpenseDTO> createOperationExpense(@RequestBody OperationExpenseDTO operationExpenseDTO) {
        try {
            OperationExpenseDTO resultDTO = operationExpenseService.save(operationExpenseDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(resultDTO);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{operationId}/{expenseId}")
    public ResponseEntity<OperationExpenseDTO> updateOperationExpense(
            @PathVariable Long operationId,
            @PathVariable Long expenseId,
            @RequestBody OperationExpenseDTO operationExpenseDTO) {
        
        OperationExpenseId id = new OperationExpenseId(operationId, expenseId);
        
        if (!operationExpenseService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        
        try {
            OperationExpenseDTO resultDTO = operationExpenseService.update(id, operationExpenseDTO);
            return ResponseEntity.ok(resultDTO);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{operationId}/{expenseId}")
    public ResponseEntity<Void> deleteOperationExpense(
            @PathVariable Long operationId,
            @PathVariable Long expenseId) {
        
        OperationExpenseId id = new OperationExpenseId(operationId, expenseId);
        
        if (!operationExpenseService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        
        operationExpenseService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/operation/{operationId}")
    public ResponseEntity<Void> deleteOperationExpensesByOperationId(@PathVariable Integer operationId) {
        operationExpenseService.deleteByOperationId(operationId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/expense/{expenseId}")
    public ResponseEntity<Void> deleteOperationExpensesByExpenseId(@PathVariable Integer expenseId) {
        operationExpenseService.deleteByExpenseId(expenseId);
        return ResponseEntity.noContent().build();
    }
}