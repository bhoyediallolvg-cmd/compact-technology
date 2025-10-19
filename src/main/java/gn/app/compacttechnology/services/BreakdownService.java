package gn.app.compacttechnology.services;

import gn.app.compacttechnology.models.dtos.BreakdownDTO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface BreakdownService {
    
    List<BreakdownDTO> findAll();
    
    Optional<BreakdownDTO> findById(Integer id);
    
    List<BreakdownDTO> findByType(Integer type);
    
    List<BreakdownDTO> findByDescriptionContaining(String description);
    
    List<BreakdownDTO> findByBreakedAtBetween(LocalDateTime start, LocalDateTime end);
    
    List<BreakdownDTO> findByFixedAtBetween(LocalDateTime start, LocalDateTime end);
    
    List<BreakdownDTO> findByCreatedAtBetween(LocalDateTime start, LocalDateTime end);
    
    List<BreakdownDTO> findByWorkforceCostGreaterThanEqual(Integer minCost);
    
    List<BreakdownDTO> findByPartsCostGreaterThanEqual(Integer minCost);
    
    List<BreakdownDTO> findByFixedAtIsNull();
    
    BreakdownDTO save(BreakdownDTO breakdownDTO);
    
    BreakdownDTO update(Integer id, BreakdownDTO breakdownDTO);
    
    void delete(Integer id);
    
    boolean existsById(Integer id);
}