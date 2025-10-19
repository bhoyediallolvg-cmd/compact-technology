package gn.app.compacttechnology.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO {
    
    private Long id;
    private String customerNumber;
    private Integer type;
    private String address;
    private Long enterpriseId;
    private EnterpriseDTO enterprise;
}