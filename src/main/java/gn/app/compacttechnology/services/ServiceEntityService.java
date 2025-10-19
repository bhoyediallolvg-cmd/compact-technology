package gn.app.compacttechnology.services;

import gn.app.compacttechnology.models.dtos.ServiceDTO;

import java.util.List;
import java.util.Optional;

public interface ServiceEntityService {
    
    List<ServiceDTO> findAll();
    
    Optional<ServiceDTO> findById(Integer id);
    
    Optional<ServiceDTO> findByReference(String reference);
    
    List<ServiceDTO> findByEnterpriseId(Integer enterpriseId);
    
    List<ServiceDTO> findByDescriptionContaining(String description);
    
    List<ServiceDTO> findByPriceLessThanEqual(Integer maxPrice);
    
    List<ServiceDTO> findByPriceGreaterThanEqual(Integer minPrice);
    
    ServiceDTO save(ServiceDTO serviceDTO);
    
    ServiceDTO update(Integer id, ServiceDTO serviceDTO);
    
    void delete(Integer id);
    
    boolean existsById(Integer id);
    
    boolean existsByReference(String reference);
}