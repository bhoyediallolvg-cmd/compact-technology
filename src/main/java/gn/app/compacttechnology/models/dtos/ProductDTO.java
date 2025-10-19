package gn.app.compacttechnology.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    
    private Long id;
    private String reference;
    private String description;
    private Double price;
    private Integer quantity;
    private String type;
    private Long enterpriseId;
    private EnterpriseDTO enterprise;
}