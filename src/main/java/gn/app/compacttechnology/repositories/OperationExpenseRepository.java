package gn.app.compacttechnology.repositories;

import gn.app.compacttechnology.models.entities.Expense;
import gn.app.compacttechnology.models.entities.Operation;
import gn.app.compacttechnology.models.entities.OperationExpense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OperationExpenseRepository extends JpaRepository<OperationExpense, OperationExpense.OperationExpenseId> {
    
    List<OperationExpense> findByOperation(Operation operation);
    
    List<OperationExpense> findByOperationId(Long operationId);
    
    List<OperationExpense> findByExpense(Expense expense);
    
    List<OperationExpense> findByExpenseId(Long expenseId);
    
    List<OperationExpense> findByCostGreaterThanEqual(Double minCost);
    
    List<OperationExpense> findByCostLessThanEqual(Double maxCost);
    
    void deleteByOperation(Operation operation);
    
    void deleteByExpense(Expense expense);
}