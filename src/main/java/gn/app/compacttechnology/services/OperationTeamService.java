package gn.app.compacttechnology.services;

import gn.app.compacttechnology.models.dtos.OperationTeamDTO;
import gn.app.compacttechnology.models.entities.OperationTeam;

import java.util.List;
import java.util.Optional;

public interface OperationTeamService {
    
    List<OperationTeamDTO> findAll();
    
    Optional<OperationTeamDTO> findById(OperationTeam.OperationTeamId id);
    
    List<OperationTeamDTO> findByOperationId(Integer operationId);
    
    List<OperationTeamDTO> findByUserId(Integer userId);
    
    OperationTeamDTO save(OperationTeamDTO operationTeamDTO);
    
    OperationTeamDTO update(OperationTeam.OperationTeamId id, OperationTeamDTO operationTeamDTO);
    
    void delete(OperationTeam.OperationTeamId id);
    
    void deleteByOperationId(Integer operationId);
    
    void deleteByUserId(Integer userId);
    
    boolean existsById(OperationTeam.OperationTeamId id);
}