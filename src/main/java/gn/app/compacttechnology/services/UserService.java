package gn.app.compacttechnology.services;

import gn.app.compacttechnology.models.dtos.UserDTO;

import java.util.List;
import java.util.Optional;

public interface UserService {
    
    List<UserDTO> findAll();
    
    Optional<UserDTO> findById(Integer id);
    
    Optional<UserDTO> findByRegistrationNumber(String registrationNumber);
    
    List<UserDTO> findByEnterpriseId(Integer enterpriseId);
    
    List<UserDTO> findByJobId(Integer jobId);
    
    List<UserDTO> findByStatus(Integer status);
    
    List<UserDTO> findByContractType(Integer contractType);
    
    List<UserDTO> findByName(String name);
    
    UserDTO save(UserDTO userDTO);
    
    UserDTO update(Integer id, UserDTO userDTO);
    
    void delete(Integer id);
    
    boolean existsById(Integer id);
    
    boolean existsByRegistrationNumber(String registrationNumber);
}