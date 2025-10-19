package gn.app.compacttechnology.repositories;

import gn.app.compacttechnology.models.entities.Breakdown;
import gn.app.compacttechnology.models.entities.Operation;
import gn.app.compacttechnology.models.entities.Vehicle;
import gn.app.compacttechnology.models.entities.VehicleBreakdown;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VehicleBreakdownRepository extends JpaRepository<VehicleBreakdown, VehicleBreakdown.VehicleBreakdownId> {
    
    List<VehicleBreakdown> findByVehicle(Vehicle vehicle);
    
    List<VehicleBreakdown> findByVehicleId(Long vehicleId);
    
    List<VehicleBreakdown> findByBreakdown(Breakdown breakdown);
    
    List<VehicleBreakdown> findByBreakdownId(Long breakdownId);
    
    List<VehicleBreakdown> findByOperation(Operation operation);
    
    List<VehicleBreakdown> findByOperationId(Long operationId);
    
    List<VehicleBreakdown> findByOperationIsNull();
    
    Optional<VehicleBreakdown> findByVehicleAndBreakdown(Vehicle vehicle, Breakdown breakdown);
    
    void deleteByVehicle(Vehicle vehicle);
    
    void deleteByBreakdown(Breakdown breakdown);
    
    void deleteByOperation(Operation operation);
}