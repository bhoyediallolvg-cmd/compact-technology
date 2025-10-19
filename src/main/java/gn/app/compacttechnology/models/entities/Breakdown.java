package gn.app.compacttechnology.models.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "breakdown")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Breakdown {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "duration")
    private Integer duration;
    
    private Integer type;
    
    @Column(columnDefinition = "text")
    private String description;
    
    @Column(name = "workforce_cost")
    private Double workforceCost;
    
    @Column(columnDefinition = "json")
    private String parts;
    
    @Column(name = "parts_cost")
    private Double partsCost;
    
    @Column(name = "breaked_at")
    private LocalDateTime breakedAt;
    
    @Column(name = "fixed_at")
    private LocalDateTime fixedAt;
    
    @Column(name = "created_at")
    private LocalDateTime createdAt;
}