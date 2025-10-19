package gn.app.compacttechnology.models.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "enterprise")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Enterprise {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String name;
    
    private String logo;
    
    private String description;
    
    private String address;
    
    @Column(unique = true)
    private String subdomain;
}