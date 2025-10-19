package gn.app.compacttechnology.services;

import gn.app.compacttechnology.models.dtos.EnterpriseDTO;

import java.util.List;
import java.util.Optional;

public interface EnterpriseService {
    
    List<EnterpriseDTO> findAll();
    
    Optional<EnterpriseDTO> findById(Long id);
    
    Optional<EnterpriseDTO> findBySubdomain(String subdomain);
    
    EnterpriseDTO save(EnterpriseDTO enterpriseDTO);
    
    EnterpriseDTO update(Long id, EnterpriseDTO enterpriseDTO);
    
    void delete(Long id);
    
    boolean existsById(Long id);
    
    boolean existsBySubdomain(String subdomain);
}