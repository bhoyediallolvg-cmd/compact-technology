package gn.app.compacttechnology.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WorkSiteDTO {
    
    private Long id;
    private String reference;
    private String address;
    private Long customerId;
    private CustomerDTO customer;
}