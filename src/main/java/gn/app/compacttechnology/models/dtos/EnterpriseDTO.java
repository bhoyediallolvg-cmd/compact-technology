package gn.app.compacttechnology.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EnterpriseDTO {
    
    private Long id;
    private String name;
    private String logo;
    private String description;
    private String address;
    private String subdomain;
}