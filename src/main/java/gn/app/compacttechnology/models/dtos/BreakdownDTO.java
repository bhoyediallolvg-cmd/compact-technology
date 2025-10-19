package gn.app.compacttechnology.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BreakdownDTO {
    
    private Long id;
    private Integer duration;
    private String type;
    private String description;
    private Integer workforceCost;
    private String parts;
    private Double partsCost;
    private LocalDateTime breakedAt;
    private LocalDateTime fixedAt;
    private LocalDateTime createdAt;
}