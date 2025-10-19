package gn.app.compacttechnology.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OperationExpenseDTO {
    
    private Long operationId;
    private Integer expenseId;
    private Integer cost;
    
    private OperationDTO operation;
    private ExpenseDTO expense;
}