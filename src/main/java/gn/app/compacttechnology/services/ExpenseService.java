package gn.app.compacttechnology.services;

import gn.app.compacttechnology.models.dtos.ExpenseDTO;

import java.util.List;
import java.util.Optional;

public interface ExpenseService {
    
    List<ExpenseDTO> findAll();
    
    Optional<ExpenseDTO> findById(Integer id);
    
    List<ExpenseDTO> findByNameContaining(String name);
    
    ExpenseDTO save(ExpenseDTO expenseDTO);
    
    ExpenseDTO update(Integer id, ExpenseDTO expenseDTO);
    
    void delete(Integer id);
    
    boolean existsById(Integer id);
    
    boolean existsByName(String name);
}