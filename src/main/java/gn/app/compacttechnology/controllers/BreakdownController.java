package gn.app.compacttechnology.controllers;

import gn.app.compacttechnology.models.dtos.BreakdownDTO;
import gn.app.compacttechnology.services.BreakdownService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/breakdowns")
@RequiredArgsConstructor
public class BreakdownController {

    private final BreakdownService breakdownService;

    @GetMapping
    public ResponseEntity<List<BreakdownDTO>> getAllBreakdowns() {
        return ResponseEntity.ok(breakdownService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BreakdownDTO> getBreakdownById(@PathVariable Integer id) {
        return breakdownService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/type/{type}")
    public ResponseEntity<List<BreakdownDTO>> getBreakdownsByType(@PathVariable Integer type) {
        return ResponseEntity.ok(breakdownService.findByType(type));
    }

    @GetMapping("/search")
    public ResponseEntity<List<BreakdownDTO>> searchBreakdowns(@RequestParam String description) {
        return ResponseEntity.ok(breakdownService.findByDescriptionContaining(description));
    }

    @GetMapping("/breaked-between")
    public ResponseEntity<List<BreakdownDTO>> getBreakdownsBreakedBetween(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end) {
        return ResponseEntity.ok(breakdownService.findByBreakedAtBetween(start, end));
    }

    @GetMapping("/fixed-between")
    public ResponseEntity<List<BreakdownDTO>> getBreakdownsFixedBetween(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end) {
        return ResponseEntity.ok(breakdownService.findByFixedAtBetween(start, end));
    }

    @GetMapping("/created-between")
    public ResponseEntity<List<BreakdownDTO>> getBreakdownsCreatedBetween(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end) {
        return ResponseEntity.ok(breakdownService.findByCreatedAtBetween(start, end));
    }

    @GetMapping("/workforce-cost")
    public ResponseEntity<List<BreakdownDTO>> getBreakdownsByMinWorkforceCost(@RequestParam Integer minCost) {
        return ResponseEntity.ok(breakdownService.findByWorkforceCostGreaterThanEqual(minCost));
    }

    @GetMapping("/parts-cost")
    public ResponseEntity<List<BreakdownDTO>> getBreakdownsByMinPartsCost(@RequestParam Integer minCost) {
        return ResponseEntity.ok(breakdownService.findByPartsCostGreaterThanEqual(minCost));
    }

    @GetMapping("/unfixed")
    public ResponseEntity<List<BreakdownDTO>> getUnfixedBreakdowns() {
        return ResponseEntity.ok(breakdownService.findByFixedAtIsNull());
    }

    @PostMapping
    public ResponseEntity<BreakdownDTO> createBreakdown(@RequestBody BreakdownDTO breakdownDTO) {
        try {
            BreakdownDTO resultDTO = breakdownService.save(breakdownDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(resultDTO);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<BreakdownDTO> updateBreakdown(@PathVariable Integer id, @RequestBody BreakdownDTO breakdownDTO) {
        if (!breakdownService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        try {
            BreakdownDTO resultDTO = breakdownService.update(id, breakdownDTO);
            return ResponseEntity.ok(resultDTO);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBreakdown(@PathVariable Integer id) {
        if (!breakdownService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        breakdownService.delete(id);
        return ResponseEntity.noContent().build();
    }
}