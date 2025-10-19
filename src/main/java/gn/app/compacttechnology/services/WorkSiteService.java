package gn.app.compacttechnology.services;

import gn.app.compacttechnology.models.dtos.WorkSiteDTO;

import java.util.List;
import java.util.Optional;

public interface WorkSiteService {
    
    List<WorkSiteDTO> findAll();
    
    Optional<WorkSiteDTO> findById(Integer id);
    
    Optional<WorkSiteDTO> findByReference(String reference);
    
    List<WorkSiteDTO> findByCustomerId(Integer customerId);
    
    List<WorkSiteDTO> findByAddressContaining(String address);
    
    List<WorkSiteDTO> findByReferenceContaining(String reference);
    
    WorkSiteDTO save(WorkSiteDTO workSiteDTO);
    
    WorkSiteDTO update(Integer id, WorkSiteDTO workSiteDTO);
    
    void delete(Integer id);
    
    boolean existsById(Integer id);
}