package gn.app.compacttechnology.models.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "operation_team")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OperationTeam {
    
    @EmbeddedId
    private OperationTeamId id;
    
    @ManyToOne
    @MapsId("operationId")
    @JoinColumn(name = "operation_id")
    private Operation operation;
    
    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private User user;
    
    @Embeddable
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class OperationTeamId implements java.io.Serializable {
        
        private static final long serialVersionUID = 1L;
        
        @Column(name = "operation_id")
        private Long operationId;
        
        @Column(name = "user_id")
        private Long userId;
    }
}