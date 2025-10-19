package gn.app.compacttechnology.mappers;

import gn.app.compacttechnology.models.dtos.OperationExpenseDTO;
import gn.app.compacttechnology.models.entities.Operation;
import gn.app.compacttechnology.models.entities.Expense;
import gn.app.compacttechnology.models.entities.OperationExpense;
import gn.app.compacttechnology.models.entities.OperationExpense.OperationExpenseId;
import org.mapstruct.*;

import java.util.List;

/**
 * Mapper for the entity {@link OperationExpense} and its DTO {@link OperationExpenseDTO}.
 */
@Mapper(componentModel = "spring", uses = {OperationMapper.class, ExpenseMapper.class})
public interface OperationExpenseMapper {

    @Mapping(target = "operationId", source = "id.operationId")
    @Mapping(target = "expenseId", source = "id.expenseId")
    @Mapping(target = "cost", expression = "java(entity.getCost() != null ? entity.getCost().intValue() : null)")
    OperationExpenseDTO toDto(OperationExpense entity);

    @Mapping(target = "id", expression = "java(createOperationExpenseId(dto.getOperationId(), dto.getExpenseId()))")
    @Mapping(target = "operation", source = "operationId", qualifiedByName = "operationFromId")
    @Mapping(target = "expense", source = "expenseId", qualifiedByName = "expenseFromId")
    @Mapping(target = "cost", expression = "java(dto.getCost() != null ? dto.getCost().doubleValue() : null)")
    OperationExpense toEntity(OperationExpenseDTO dto);

    List<OperationExpenseDTO> toDto(List<OperationExpense> entityList);
    List<OperationExpense> toEntity(List<OperationExpenseDTO> dtoList);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "operation", ignore = true)
    @Mapping(target = "expense", ignore = true)
    @Mapping(target = "cost", expression = "java(dto.getCost() != null ? dto.getCost().doubleValue() : entity.getCost())")
    OperationExpense partialUpdate(OperationExpenseDTO dto, @MappingTarget OperationExpense entity);

    @Named("operationFromId")
    default Operation operationFromId(Long id) {
        if (id == null) {
            return null;
        }
        Operation operation = new Operation();
        operation.setId(id);
        return operation;
    }

    @Named("expenseFromId")
    default Expense expenseFromId(Integer id) {
        if (id == null) {
            return null;
        }
        Expense expense = new Expense();
        expense.setId(id.longValue());
        return expense;
    }

    default OperationExpenseId createOperationExpenseId(Long operationId, Integer expenseId) {
        if (operationId == null || expenseId == null) {
            return null;
        }
        return new OperationExpenseId(operationId, expenseId.longValue());
    }
}