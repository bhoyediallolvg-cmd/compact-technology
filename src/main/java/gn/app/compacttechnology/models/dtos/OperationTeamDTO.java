package gn.app.compacttechnology.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OperationTeamDTO {
    
    private Long operationId;
    private Long userId;
    
    private OperationDTO operation;
    private UserDTO user;
}