package gn.app.compacttechnology.services.impl;

import gn.app.compacttechnology.mappers.VehicleBreakdownMapper;
import gn.app.compacttechnology.models.dtos.VehicleBreakdownDTO;
import gn.app.compacttechnology.models.entities.Breakdown;
import gn.app.compacttechnology.models.entities.Operation;
import gn.app.compacttechnology.models.entities.Vehicle;
import gn.app.compacttechnology.models.entities.VehicleBreakdown;
import gn.app.compacttechnology.models.entities.VehicleBreakdown.VehicleBreakdownId;
import gn.app.compacttechnology.repositories.VehicleBreakdownRepository;
import gn.app.compacttechnology.services.VehicleBreakdownService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class VehicleBreakdownServiceImpl implements VehicleBreakdownService {

    private final VehicleBreakdownRepository vehicleBreakdownRepository;
    private final VehicleBreakdownMapper vehicleBreakdownMapper;

    public VehicleBreakdownServiceImpl(VehicleBreakdownRepository vehicleBreakdownRepository, VehicleBreakdownMapper vehicleBreakdownMapper) {
        this.vehicleBreakdownRepository = vehicleBreakdownRepository;
        this.vehicleBreakdownMapper = vehicleBreakdownMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<VehicleBreakdownDTO> findAll() {
        return vehicleBreakdownMapper.toDto(vehicleBreakdownRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<VehicleBreakdownDTO> findById(VehicleBreakdownId id) {
        return vehicleBreakdownRepository.findById(id)
                .map(vehicleBreakdownMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<VehicleBreakdownDTO> findByVehicleId(Integer vehicleId) {
        return vehicleBreakdownMapper.toDto(vehicleBreakdownRepository.findByVehicleId(vehicleId.longValue()));
    }

    @Override
    @Transactional(readOnly = true)
    public List<VehicleBreakdownDTO> findByBreakdownId(Integer breakdownId) {
        return vehicleBreakdownMapper.toDto(vehicleBreakdownRepository.findByBreakdownId(breakdownId.longValue()));
    }

    @Override
    @Transactional(readOnly = true)
    public List<VehicleBreakdownDTO> findByOperationId(Integer operationId) {
        return vehicleBreakdownMapper.toDto(vehicleBreakdownRepository.findByOperationId(operationId.longValue()));
    }

    @Override
    @Transactional(readOnly = true)
    public List<VehicleBreakdownDTO> findByOperationIsNull() {
        return vehicleBreakdownMapper.toDto(vehicleBreakdownRepository.findByOperationIsNull());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<VehicleBreakdownDTO> findByVehicleIdAndBreakdownId(Integer vehicleId, Integer breakdownId) {
        Vehicle vehicle = new Vehicle();
        vehicle.setId(vehicleId.longValue());
        
        Breakdown breakdown = new Breakdown();
        breakdown.setId(breakdownId.longValue());
        
        return vehicleBreakdownRepository.findByVehicleAndBreakdown(vehicle, breakdown)
                .map(vehicleBreakdownMapper::toDto);
    }

    @Override
    public VehicleBreakdownDTO save(VehicleBreakdownDTO vehicleBreakdownDTO) {
        VehicleBreakdown vehicleBreakdown = vehicleBreakdownMapper.toEntity(vehicleBreakdownDTO);
        vehicleBreakdown = vehicleBreakdownRepository.save(vehicleBreakdown);
        return vehicleBreakdownMapper.toDto(vehicleBreakdown);
    }

    @Override
    public VehicleBreakdownDTO update(VehicleBreakdownId id, VehicleBreakdownDTO vehicleBreakdownDTO) {
        VehicleBreakdown existingVehicleBreakdown = vehicleBreakdownRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("VehicleBreakdown not found with id: " + id));

        // Update fields using MapStruct's partialUpdate
        vehicleBreakdownMapper.partialUpdate(vehicleBreakdownDTO, existingVehicleBreakdown);

        VehicleBreakdown updatedVehicleBreakdown = vehicleBreakdownRepository.save(existingVehicleBreakdown);
        return vehicleBreakdownMapper.toDto(updatedVehicleBreakdown);
    }

    @Override
    public void delete(VehicleBreakdownId id) {
        vehicleBreakdownRepository.deleteById(id);
    }

    @Override
    public void deleteByVehicleId(Integer vehicleId) {
        Vehicle vehicle = new Vehicle();
        vehicle.setId(vehicleId.longValue());
        vehicleBreakdownRepository.deleteByVehicle(vehicle);
    }

    @Override
    public void deleteByBreakdownId(Integer breakdownId) {
        Breakdown breakdown = new Breakdown();
        breakdown.setId(breakdownId.longValue());
        vehicleBreakdownRepository.deleteByBreakdown(breakdown);
    }

    @Override
    public void deleteByOperationId(Integer operationId) {
        Operation operation = new Operation();
        operation.setId(operationId.longValue());
        vehicleBreakdownRepository.deleteByOperation(operation);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsById(VehicleBreakdownId id) {
        return vehicleBreakdownRepository.existsById(id);
    }
}