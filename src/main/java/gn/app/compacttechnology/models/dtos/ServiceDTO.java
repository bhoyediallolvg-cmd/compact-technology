package gn.app.compacttechnology.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServiceDTO {
    
    private Long id;
    private String reference;
    private String description;
    private Double price;
    private Long enterpriseId;
    private EnterpriseDTO enterprise;
}