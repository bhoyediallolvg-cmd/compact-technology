package gn.app.compacttechnology.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OperationDTO {
    
    private Long id;
    private String reference;
    private LocalDateTime startAt;
    private LocalDateTime endAt;
    private LocalDateTime startedAt;
    private LocalDateTime finishedAt;
    private Integer numberOfTravels;
    
    private Long workSiteId;
    private WorkSiteDTO workSite;
    
    private Long serviceId;
    private ServiceDTO service;
    
    private Long customerId;
    private CustomerDTO customer;
    
    private Long vehicleId;
    private VehicleDTO vehicle;
    
    private Long teamLeadId;
    private UserDTO teamLead;
}