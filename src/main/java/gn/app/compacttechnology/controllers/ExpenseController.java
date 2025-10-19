package gn.app.compacttechnology.controllers;

import gn.app.compacttechnology.models.dtos.ExpenseDTO;
import gn.app.compacttechnology.services.ExpenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/expenses")
@RequiredArgsConstructor
public class ExpenseController {

    private final ExpenseService expenseService;

    @GetMapping
    public ResponseEntity<List<ExpenseDTO>> getAllExpenses() {
        return ResponseEntity.ok(expenseService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExpenseDTO> getExpenseById(@PathVariable Integer id) {
        return expenseService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/search")
    public ResponseEntity<List<ExpenseDTO>> getExpensesByName(@RequestParam String name) {
        return ResponseEntity.ok(expenseService.findByNameContaining(name));
    }

    @PostMapping
    public ResponseEntity<ExpenseDTO> createExpense(@RequestBody ExpenseDTO expenseDTO) {
        try {
            ExpenseDTO resultDTO = expenseService.save(expenseDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(resultDTO);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ExpenseDTO> updateExpense(@PathVariable Integer id, @RequestBody ExpenseDTO expenseDTO) {
        if (!expenseService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        try {
            ExpenseDTO resultDTO = expenseService.update(id, expenseDTO);
            return ResponseEntity.ok(resultDTO);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExpense(@PathVariable Integer id) {
        if (!expenseService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        expenseService.delete(id);
        return ResponseEntity.noContent().build();
    }
}