package gn.app.compacttechnology.services.impl;

import gn.app.compacttechnology.mappers.OperationMapper;
import gn.app.compacttechnology.models.dtos.OperationDTO;
import gn.app.compacttechnology.models.entities.Operation;
import gn.app.compacttechnology.repositories.OperationRepository;
import gn.app.compacttechnology.services.OperationService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class OperationServiceImpl implements OperationService {

    private final OperationRepository operationRepository;
    private final OperationMapper operationMapper;

    public OperationServiceImpl(OperationRepository operationRepository, OperationMapper operationMapper) {
        this.operationRepository = operationRepository;
        this.operationMapper = operationMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<OperationDTO> findAll() {
        return operationMapper.toDto(operationRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<OperationDTO> findById(Integer id) {
        return operationRepository.findById(id.longValue())
                .map(operationMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<OperationDTO> findByReference(String reference) {
        return operationRepository.findByReference(reference)
                .map(operationMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<OperationDTO> findByStartAtBetween(LocalDateTime start, LocalDateTime end) {
        return operationMapper.toDto(operationRepository.findByStartAtBetween(start, end));
    }

    @Override
    @Transactional(readOnly = true)
    public List<OperationDTO> findByEndAtBetween(LocalDateTime start, LocalDateTime end) {
        return operationMapper.toDto(operationRepository.findByEndAtBetween(start, end));
    }

    @Override
    @Transactional(readOnly = true)
    public List<OperationDTO> findByStartedAtBetween(LocalDateTime start, LocalDateTime end) {
        return operationMapper.toDto(operationRepository.findByStartedAtBetween(start, end));
    }

    @Override
    @Transactional(readOnly = true)
    public List<OperationDTO> findByFinishedAtBetween(LocalDateTime start, LocalDateTime end) {
        return operationMapper.toDto(operationRepository.findByFinishedAtBetween(start, end));
    }

    @Override
    @Transactional(readOnly = true)
    public List<OperationDTO> findByWorkSiteId(Integer workSiteId) {
        return operationMapper.toDto(operationRepository.findByWorkSiteId(workSiteId.longValue()));
    }

    @Override
    @Transactional(readOnly = true)
    public List<OperationDTO> findByServiceId(Integer serviceId) {
        return operationMapper.toDto(operationRepository.findByServiceId(serviceId.longValue()));
    }

    @Override
    @Transactional(readOnly = true)
    public List<OperationDTO> findByCustomerId(Integer customerId) {
        return operationMapper.toDto(operationRepository.findByCustomerId(customerId.longValue()));
    }

    @Override
    @Transactional(readOnly = true)
    public List<OperationDTO> findByVehicleId(Integer vehicleId) {
        return operationMapper.toDto(operationRepository.findByVehicleId(vehicleId.longValue()));
    }

    @Override
    @Transactional(readOnly = true)
    public List<OperationDTO> findByTeamLeadId(Integer teamLeadId) {
        return operationMapper.toDto(operationRepository.findByTeamLeadId(teamLeadId.longValue()));
    }

    @Override
    @Transactional(readOnly = true)
    public List<OperationDTO> findByStartedAtIsNull() {
        return operationMapper.toDto(operationRepository.findByStartedAtIsNull());
    }

    @Override
    @Transactional(readOnly = true)
    public List<OperationDTO> findByFinishedAtIsNull() {
        return operationMapper.toDto(operationRepository.findByFinishedAtIsNull());
    }

    @Override
    public OperationDTO save(OperationDTO operationDTO) {
        Operation operation = operationMapper.toEntity(operationDTO);
        operation = operationRepository.save(operation);
        return operationMapper.toDto(operation);
    }

    @Override
    public OperationDTO update(Integer id, OperationDTO operationDTO) {
        Operation existingOperation = operationRepository.findById(id.longValue())
                .orElseThrow(() -> new RuntimeException("Operation not found with id: " + id));

        // Update fields using MapStruct's partialUpdate
        operationMapper.partialUpdate(operationDTO, existingOperation);

        Operation updatedOperation = operationRepository.save(existingOperation);
        return operationMapper.toDto(updatedOperation);
    }

    @Override
    public void delete(Integer id) {
        operationRepository.deleteById(id.longValue());
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsById(Integer id) {
        return operationRepository.existsById(id.longValue());
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsByReference(String reference) {
        return operationRepository.existsByReference(reference);
    }
}