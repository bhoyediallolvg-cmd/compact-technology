package gn.app.compacttechnology.services;

import gn.app.compacttechnology.models.dtos.CustomerDTO;

import java.util.List;
import java.util.Optional;

public interface CustomerService {
    
    List<CustomerDTO> findAll();
    
    Optional<CustomerDTO> findById(Integer id);
    
    Optional<CustomerDTO> findByCustomerNumber(String customerNumber);
    
    List<CustomerDTO> findByEnterpriseId(Integer enterpriseId);
    
    CustomerDTO save(CustomerDTO customerDTO);
    
    CustomerDTO update(Integer id, CustomerDTO customerDTO);
    
    void delete(Integer id);
    
    boolean existsById(Integer id);
    
    boolean existsByCustomerNumber(String customerNumber);
    
    List<CustomerDTO> findByType(Integer type);
}