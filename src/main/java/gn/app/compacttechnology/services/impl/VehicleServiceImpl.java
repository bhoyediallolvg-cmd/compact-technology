package gn.app.compacttechnology.services.impl;

import gn.app.compacttechnology.mappers.VehicleMapper;
import gn.app.compacttechnology.models.dtos.VehicleDTO;
import gn.app.compacttechnology.models.entities.Vehicle;
import gn.app.compacttechnology.repositories.VehicleRepository;
import gn.app.compacttechnology.services.VehicleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class VehicleServiceImpl implements VehicleService {

    private final VehicleRepository vehicleRepository;
    private final VehicleMapper vehicleMapper;

    public VehicleServiceImpl(VehicleRepository vehicleRepository, VehicleMapper vehicleMapper) {
        this.vehicleRepository = vehicleRepository;
        this.vehicleMapper = vehicleMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<VehicleDTO> findAll() {
        return vehicleMapper.toDto(vehicleRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<VehicleDTO> findById(Integer id) {
        return vehicleRepository.findById(id.longValue())
                .map(vehicleMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<VehicleDTO> findByRegistrationNumber(String registrationNumber) {
        return vehicleRepository.findByRegistrationNumber(registrationNumber)
                .map(vehicleMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<VehicleDTO> findByEnterpriseId(Integer enterpriseId) {
        return vehicleMapper.toDto(vehicleRepository.findByEnterpriseId(enterpriseId.longValue()));
    }

    @Override
    @Transactional(readOnly = true)
    public List<VehicleDTO> findByStatus(Integer status) {
        return vehicleMapper.toDto(vehicleRepository.findByStatus(status));
    }

    @Override
    @Transactional(readOnly = true)
    public List<VehicleDTO> findByType(Integer type) {
        return vehicleMapper.toDto(vehicleRepository.findByType(type.toString()));
    }

    @Override
    public VehicleDTO save(VehicleDTO vehicleDTO) {
        Vehicle vehicle = vehicleMapper.toEntity(vehicleDTO);
        vehicle = vehicleRepository.save(vehicle);
        return vehicleMapper.toDto(vehicle);
    }

    @Override
    public VehicleDTO update(Integer id, VehicleDTO vehicleDTO) {
        Vehicle existingVehicle = vehicleRepository.findById(id.longValue())
                .orElseThrow(() -> new RuntimeException("Vehicle not found with id: " + id));

        // Update fields using MapStruct's partialUpdate
        vehicleMapper.partialUpdate(vehicleDTO, existingVehicle);

        Vehicle updatedVehicle = vehicleRepository.save(existingVehicle);
        return vehicleMapper.toDto(updatedVehicle);
    }

    @Override
    public void delete(Integer id) {
        vehicleRepository.deleteById(id.longValue());
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsById(Integer id) {
        return vehicleRepository.existsById(id.longValue());
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsByRegistrationNumber(String registrationNumber) {
        return vehicleRepository.existsByRegistrationNumber(registrationNumber);
    }
}