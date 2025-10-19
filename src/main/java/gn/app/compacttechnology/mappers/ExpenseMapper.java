package gn.app.compacttechnology.mappers;

import gn.app.compacttechnology.models.dtos.ExpenseDTO;
import gn.app.compacttechnology.models.entities.Expense;
import org.mapstruct.*;

import java.util.List;

/**
 * Mapper for the entity {@link Expense} and its DTO {@link ExpenseDTO}.
 */
@Mapper(componentModel = "spring")
public interface ExpenseMapper {

    ExpenseDTO toDto(Expense entity);

    Expense toEntity(ExpenseDTO dto);

    List<ExpenseDTO> toDto(List<Expense> entityList);
    
    List<Expense> toEntity(List<ExpenseDTO> dtoList);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Expense partialUpdate(ExpenseDTO dto, @MappingTarget Expense entity);
}