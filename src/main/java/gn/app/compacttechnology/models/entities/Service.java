package gn.app.compacttechnology.models.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "service")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Service {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(unique = true, nullable = false)
    private String reference;
    
    private String description;
    
    private Double price;
    
    @ManyToOne
    @JoinColumn(name = "enterprise_id", nullable = false)
    private Enterprise enterprise;
}