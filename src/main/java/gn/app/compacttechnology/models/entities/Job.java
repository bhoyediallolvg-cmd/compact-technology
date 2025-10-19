package gn.app.compacttechnology.models.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "job")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Job {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    
    private String category;
    
    @ManyToOne
    @JoinColumn(name = "enterprise_id", nullable = false)
    private Enterprise enterprise;
}