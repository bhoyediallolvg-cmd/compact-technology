package gn.app.compacttechnology.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    
    private Long id;
    private String registrationNumber;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private LocalDateTime hiredAt;
    private String phoneNumber;
    private String roles;
    private Integer contractType;
    private Integer hourlyRate;
    private Integer jobId;
    private JobDTO job;
    private Integer status;
    private Long enterpriseId;
    private EnterpriseDTO enterprise;
}