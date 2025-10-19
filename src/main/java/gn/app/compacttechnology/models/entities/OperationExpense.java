package gn.app.compacttechnology.models.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "operation_expense")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OperationExpense {
    
    @EmbeddedId
    private OperationExpenseId id;
    
    @ManyToOne
    @MapsId("operationId")
    @JoinColumn(name = "operation_id")
    private Operation operation;
    
    @ManyToOne
    @MapsId("expenseId")
    @JoinColumn(name = "expense_id")
    private Expense expense;
    
    private Double cost;
    
    @Embeddable
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class OperationExpenseId implements java.io.Serializable {
        
        private static final long serialVersionUID = 1L;
        
        @Column(name = "operation_id")
        private Long operationId;
        
        @Column(name = "expense_id")
        private Long expenseId;
    }
}