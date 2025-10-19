package gn.app.compacttechnology.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobDTO {
    
    private Long id;
    private String name;
    private Integer category;
    private Long enterpriseId;
    private EnterpriseDTO enterprise;
}