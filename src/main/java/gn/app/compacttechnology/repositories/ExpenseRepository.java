package gn.app.compacttechnology.repositories;

import gn.app.compacttechnology.models.entities.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    
    List<Expense> findByNameContainingIgnoreCase(String name);
    
    boolean existsByName(String name);
}