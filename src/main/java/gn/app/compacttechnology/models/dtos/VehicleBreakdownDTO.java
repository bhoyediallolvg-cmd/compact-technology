package gn.app.compacttechnology.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VehicleBreakdownDTO {
    
    private Long vehicleId;
    private Long breakdownId;
    private Long operationId;
    
    private VehicleDTO vehicle;
    private BreakdownDTO breakdown;
    private OperationDTO operation;
}