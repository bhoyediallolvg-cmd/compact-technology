package gn.app.compacttechnology.models.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "app_user")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "registration_number", unique = true, nullable = false)
    private String registrationNumber;
    
    @Column(name = "first_name")
    private String firstName;
    
    @Column(name = "last_name")
    private String lastName;
    
    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;
    
    @Column(name = "hired_at")
    private LocalDateTime hiredAt;
    
    @Column(name = "phone_number")
    private String phoneNumber;
    
    @Column(name = "user_roles")
    private String roles;
    
    @Column(name = "contract_type")
    private Integer contractType;
    
    @Column(name = "hourly_rate")
    private Integer hourlyRate;
    
    @ManyToOne
    @JoinColumn(name = "job_id")
    private Job job;
    
    private Integer status;
    
    @ManyToOne
    @JoinColumn(name = "enterprise_id", nullable = false)
    private Enterprise enterprise;
}