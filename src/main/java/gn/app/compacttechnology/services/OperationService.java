package gn.app.compacttechnology.services;

import gn.app.compacttechnology.models.dtos.OperationDTO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface OperationService {
    
    List<OperationDTO> findAll();
    
    Optional<OperationDTO> findById(Integer id);
    
    Optional<OperationDTO> findByReference(String reference);
    
    List<OperationDTO> findByStartAtBetween(LocalDateTime start, LocalDateTime end);
    
    List<OperationDTO> findByEndAtBetween(LocalDateTime start, LocalDateTime end);
    
    List<OperationDTO> findByStartedAtBetween(LocalDateTime start, LocalDateTime end);
    
    List<OperationDTO> findByFinishedAtBetween(LocalDateTime start, LocalDateTime end);
    
    List<OperationDTO> findByWorkSiteId(Integer workSiteId);
    
    List<OperationDTO> findByServiceId(Integer serviceId);
    
    List<OperationDTO> findByCustomerId(Integer customerId);
    
    List<OperationDTO> findByVehicleId(Integer vehicleId);
    
    List<OperationDTO> findByTeamLeadId(Integer teamLeadId);
    
    List<OperationDTO> findByStartedAtIsNull();
    
    List<OperationDTO> findByFinishedAtIsNull();
    
    OperationDTO save(OperationDTO operationDTO);
    
    OperationDTO update(Integer id, OperationDTO operationDTO);
    
    void delete(Integer id);
    
    boolean existsById(Integer id);
    
    boolean existsByReference(String reference);
}