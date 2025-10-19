package gn.app.compacttechnology.services;

import gn.app.compacttechnology.models.dtos.VehicleDTO;

import java.util.List;
import java.util.Optional;

public interface VehicleService {
    
    List<VehicleDTO> findAll();
    
    Optional<VehicleDTO> findById(Integer id);
    
    Optional<VehicleDTO> findByRegistrationNumber(String registrationNumber);
    
    List<VehicleDTO> findByEnterpriseId(Integer enterpriseId);
    
    List<VehicleDTO> findByStatus(Integer status);
    
    List<VehicleDTO> findByType(Integer type);
    
    VehicleDTO save(VehicleDTO vehicleDTO);
    
    VehicleDTO update(Integer id, VehicleDTO vehicleDTO);
    
    void delete(Integer id);
    
    boolean existsById(Integer id);
    
    boolean existsByRegistrationNumber(String registrationNumber);
}