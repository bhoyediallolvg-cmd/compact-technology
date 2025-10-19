package gn.app.compacttechnology.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VehicleDTO {
    
    private Long id;
    private String registrationNumber;
    private String brand;
    private String model;
    private String description;
    private Integer status;
    private Float volume;
    private Integer type;
    private Double price;
    private LocalDateTime acquiredAt;
    private Long enterpriseId;
    private EnterpriseDTO enterprise;
}