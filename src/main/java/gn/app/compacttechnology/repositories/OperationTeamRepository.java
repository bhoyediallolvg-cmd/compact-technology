package gn.app.compacttechnology.repositories;

import gn.app.compacttechnology.models.entities.Operation;
import gn.app.compacttechnology.models.entities.OperationTeam;
import gn.app.compacttechnology.models.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OperationTeamRepository extends JpaRepository<OperationTeam, OperationTeam.OperationTeamId> {
    
    List<OperationTeam> findByOperation(Operation operation);
    
    List<OperationTeam> findByOperationId(Long operationId);
    
    List<OperationTeam> findByUser(User user);
    
    List<OperationTeam> findByUserId(Long userId);
    
    void deleteByOperation(Operation operation);
    
    void deleteByUser(User user);
}