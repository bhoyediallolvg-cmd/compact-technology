package gn.app.compacttechnology.repositories;

import gn.app.compacttechnology.models.entities.Customer;
import gn.app.compacttechnology.models.entities.Operation;
import gn.app.compacttechnology.models.entities.Service;
import gn.app.compacttechnology.models.entities.User;
import gn.app.compacttechnology.models.entities.Vehicle;
import gn.app.compacttechnology.models.entities.WorkSite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface OperationRepository extends JpaRepository<Operation, Long> {
    
    Optional<Operation> findByReference(String reference);
    
    boolean existsByReference(String reference);
    
    List<Operation> findByStartAtBetween(LocalDateTime start, LocalDateTime end);
    
    List<Operation> findByEndAtBetween(LocalDateTime start, LocalDateTime end);
    
    List<Operation> findByStartedAtBetween(LocalDateTime start, LocalDateTime end);
    
    List<Operation> findByFinishedAtBetween(LocalDateTime start, LocalDateTime end);
    
    List<Operation> findByWorkSite(WorkSite workSite);
    
    List<Operation> findByWorkSiteId(Long workSiteId);
    
    List<Operation> findByService(Service service);
    
    List<Operation> findByServiceId(Long serviceId);
    
    List<Operation> findByCustomer(Customer customer);
    
    List<Operation> findByCustomerId(Long customerId);
    
    List<Operation> findByVehicle(Vehicle vehicle);
    
    List<Operation> findByVehicleId(Long vehicleId);
    
    List<Operation> findByTeamLead(User teamLead);
    
    List<Operation> findByTeamLeadId(Long teamLeadId);
    
    List<Operation> findByStartedAtIsNull();
    
    List<Operation> findByFinishedAtIsNull();
}