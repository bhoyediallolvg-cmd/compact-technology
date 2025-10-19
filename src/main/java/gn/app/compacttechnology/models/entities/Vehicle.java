package gn.app.compacttechnology.models.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "vehicle")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Vehicle {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "registration_number", unique = true, nullable = false)
    private String registrationNumber;
    
    private String brand;
    
    private String model;
    
    private String description;
    
    private Integer status;
    
    private Float volume;
    
    private String type;
    
    private Double price;
    
    @Column(name = "acquired_at")
    private LocalDateTime acquiredAt;
    
    @ManyToOne
    @JoinColumn(name = "enterprise_id", nullable = false)
    private Enterprise enterprise;
}