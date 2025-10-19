package gn.app.compacttechnology.services;

import gn.app.compacttechnology.models.dtos.OperationExpenseDTO;
import gn.app.compacttechnology.models.entities.OperationExpense;

import java.util.List;
import java.util.Optional;

public interface OperationExpenseService {
    
    List<OperationExpenseDTO> findAll();
    
    Optional<OperationExpenseDTO> findById(OperationExpense.OperationExpenseId id);
    
    List<OperationExpenseDTO> findByOperationId(Integer operationId);
    
    List<OperationExpenseDTO> findByExpenseId(Integer expenseId);
    
    List<OperationExpenseDTO> findByCostGreaterThanEqual(Integer minCost);
    
    List<OperationExpenseDTO> findByCostLessThanEqual(Integer maxCost);
    
    OperationExpenseDTO save(OperationExpenseDTO operationExpenseDTO);
    
    OperationExpenseDTO update(OperationExpense.OperationExpenseId id, OperationExpenseDTO operationExpenseDTO);
    
    void delete(OperationExpense.OperationExpenseId id);
    
    void deleteByOperationId(Integer operationId);
    
    void deleteByExpenseId(Integer expenseId);
    
    boolean existsById(OperationExpense.OperationExpenseId id);
}