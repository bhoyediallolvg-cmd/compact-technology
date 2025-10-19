package gn.app.compacttechnology.services.impl;

import gn.app.compacttechnology.mappers.ExpenseMapper;
import gn.app.compacttechnology.models.dtos.ExpenseDTO;
import gn.app.compacttechnology.models.entities.Expense;
import gn.app.compacttechnology.repositories.ExpenseRepository;
import gn.app.compacttechnology.services.ExpenseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ExpenseServiceImpl implements ExpenseService {

    private final ExpenseRepository expenseRepository;
    private final ExpenseMapper expenseMapper;

    public ExpenseServiceImpl(ExpenseRepository expenseRepository, ExpenseMapper expenseMapper) {
        this.expenseRepository = expenseRepository;
        this.expenseMapper = expenseMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ExpenseDTO> findAll() {
        return expenseMapper.toDto(expenseRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ExpenseDTO> findById(Integer id) {
        return expenseRepository.findById(id.longValue())
                .map(expenseMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ExpenseDTO> findByNameContaining(String name) {
        return expenseMapper.toDto(expenseRepository.findByNameContainingIgnoreCase(name));
    }

    @Override
    public ExpenseDTO save(ExpenseDTO expenseDTO) {
        Expense expense = expenseMapper.toEntity(expenseDTO);
        expense = expenseRepository.save(expense);
        return expenseMapper.toDto(expense);
    }

    @Override
    public ExpenseDTO update(Integer id, ExpenseDTO expenseDTO) {
        Expense existingExpense = expenseRepository.findById(id.longValue())
                .orElseThrow(() -> new RuntimeException("Expense not found with id: " + id));

        // Update fields using MapStruct's partialUpdate
        expenseMapper.partialUpdate(expenseDTO, existingExpense);

        Expense updatedExpense = expenseRepository.save(existingExpense);
        return expenseMapper.toDto(updatedExpense);
    }

    @Override
    public void delete(Integer id) {
        expenseRepository.deleteById(id.longValue());
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsById(Integer id) {
        return expenseRepository.existsById(id.longValue());
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsByName(String name) {
        return expenseRepository.existsByName(name);
    }
}