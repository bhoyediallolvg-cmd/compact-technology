package gn.app.compacttechnology.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContactDTO {
    
    private Long id;
    private String firstName;
    private String lastName;
    private String phone;
    private Long customerId;
    private CustomerDTO customer;
}