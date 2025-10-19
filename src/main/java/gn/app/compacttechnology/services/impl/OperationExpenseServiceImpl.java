package gn.app.compacttechnology.services.impl;

import gn.app.compacttechnology.mappers.OperationExpenseMapper;
import gn.app.compacttechnology.models.dtos.OperationExpenseDTO;
import gn.app.compacttechnology.models.entities.Expense;
import gn.app.compacttechnology.models.entities.Operation;
import gn.app.compacttechnology.models.entities.OperationExpense;
import gn.app.compacttechnology.models.entities.OperationExpense.OperationExpenseId;
import gn.app.compacttechnology.repositories.OperationExpenseRepository;
import gn.app.compacttechnology.services.OperationExpenseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class OperationExpenseServiceImpl implements OperationExpenseService {

    private final OperationExpenseRepository operationExpenseRepository;
    private final OperationExpenseMapper operationExpenseMapper;

    public OperationExpenseServiceImpl(OperationExpenseRepository operationExpenseRepository, OperationExpenseMapper operationExpenseMapper) {
        this.operationExpenseRepository = operationExpenseRepository;
        this.operationExpenseMapper = operationExpenseMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<OperationExpenseDTO> findAll() {
        return operationExpenseMapper.toDto(operationExpenseRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<OperationExpenseDTO> findById(OperationExpenseId id) {
        return operationExpenseRepository.findById(id)
                .map(operationExpenseMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<OperationExpenseDTO> findByOperationId(Integer operationId) {
        return operationExpenseMapper.toDto(operationExpenseRepository.findByOperationId(operationId.longValue()));
    }

    @Override
    @Transactional(readOnly = true)
    public List<OperationExpenseDTO> findByExpenseId(Integer expenseId) {
        return operationExpenseMapper.toDto(operationExpenseRepository.findByExpenseId(expenseId.longValue()));
    }

    @Override
    @Transactional(readOnly = true)
    public List<OperationExpenseDTO> findByCostGreaterThanEqual(Integer minCost) {
        return operationExpenseMapper.toDto(operationExpenseRepository.findByCostGreaterThanEqual(minCost.doubleValue()));
    }

    @Override
    @Transactional(readOnly = true)
    public List<OperationExpenseDTO> findByCostLessThanEqual(Integer maxCost) {
        return operationExpenseMapper.toDto(operationExpenseRepository.findByCostLessThanEqual(maxCost.doubleValue()));
    }

    @Override
    public OperationExpenseDTO save(OperationExpenseDTO operationExpenseDTO) {
        OperationExpense operationExpense = operationExpenseMapper.toEntity(operationExpenseDTO);
        operationExpense = operationExpenseRepository.save(operationExpense);
        return operationExpenseMapper.toDto(operationExpense);
    }

    @Override
    public OperationExpenseDTO update(OperationExpenseId id, OperationExpenseDTO operationExpenseDTO) {
        OperationExpense existingOperationExpense = operationExpenseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("OperationExpense not found with id: " + id));

        // Update fields using MapStruct's partialUpdate
        operationExpenseMapper.partialUpdate(operationExpenseDTO, existingOperationExpense);

        OperationExpense updatedOperationExpense = operationExpenseRepository.save(existingOperationExpense);
        return operationExpenseMapper.toDto(updatedOperationExpense);
    }

    @Override
    public void delete(OperationExpenseId id) {
        operationExpenseRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsById(OperationExpenseId id) {
        return operationExpenseRepository.existsById(id);
    }

    @Override
    public void deleteByOperationId(Integer operationId) {
        Operation operation = new Operation();
        operation.setId(operationId.longValue());
        operationExpenseRepository.deleteByOperation(operation);
    }

    @Override
    public void deleteByExpenseId(Integer expenseId) {
        Expense expense = new Expense();
        expense.setId(expenseId.longValue());
        operationExpenseRepository.deleteByExpense(expense);
    }
}
