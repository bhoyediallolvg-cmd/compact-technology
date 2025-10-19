package gn.app.compacttechnology.services;

import gn.app.compacttechnology.models.dtos.VehicleBreakdownDTO;
import gn.app.compacttechnology.models.entities.VehicleBreakdown;

import java.util.List;
import java.util.Optional;

public interface VehicleBreakdownService {
    
    List<VehicleBreakdownDTO> findAll();
    
    Optional<VehicleBreakdownDTO> findById(VehicleBreakdown.VehicleBreakdownId id);
    
    List<VehicleBreakdownDTO> findByVehicleId(Integer vehicleId);
    
    List<VehicleBreakdownDTO> findByBreakdownId(Integer breakdownId);
    
    List<VehicleBreakdownDTO> findByOperationId(Integer operationId);
    
    List<VehicleBreakdownDTO> findByOperationIsNull();
    
    Optional<VehicleBreakdownDTO> findByVehicleIdAndBreakdownId(Integer vehicleId, Integer breakdownId);
    
    VehicleBreakdownDTO save(VehicleBreakdownDTO vehicleBreakdownDTO);
    
    VehicleBreakdownDTO update(VehicleBreakdown.VehicleBreakdownId id, VehicleBreakdownDTO vehicleBreakdownDTO);
    
    void delete(VehicleBreakdown.VehicleBreakdownId id);
    
    void deleteByVehicleId(Integer vehicleId);
    
    void deleteByBreakdownId(Integer breakdownId);
    
    void deleteByOperationId(Integer operationId);
    
    boolean existsById(VehicleBreakdown.VehicleBreakdownId id);
}