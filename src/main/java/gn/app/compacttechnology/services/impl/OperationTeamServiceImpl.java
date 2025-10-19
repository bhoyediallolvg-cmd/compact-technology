package gn.app.compacttechnology.services.impl;

import gn.app.compacttechnology.mappers.OperationTeamMapper;
import gn.app.compacttechnology.models.dtos.OperationTeamDTO;
import gn.app.compacttechnology.models.entities.Operation;
import gn.app.compacttechnology.models.entities.OperationTeam;
import gn.app.compacttechnology.models.entities.OperationTeam.OperationTeamId;
import gn.app.compacttechnology.models.entities.User;
import gn.app.compacttechnology.repositories.OperationTeamRepository;
import gn.app.compacttechnology.services.OperationTeamService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class OperationTeamServiceImpl implements OperationTeamService {

    private final OperationTeamRepository operationTeamRepository;
    private final OperationTeamMapper operationTeamMapper;

    public OperationTeamServiceImpl(OperationTeamRepository operationTeamRepository, OperationTeamMapper operationTeamMapper) {
        this.operationTeamRepository = operationTeamRepository;
        this.operationTeamMapper = operationTeamMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<OperationTeamDTO> findAll() {
        return operationTeamMapper.toDto(operationTeamRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<OperationTeamDTO> findById(OperationTeamId id) {
        return operationTeamRepository.findById(id)
                .map(operationTeamMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<OperationTeamDTO> findByOperationId(Integer operationId) {
        return operationTeamMapper.toDto(operationTeamRepository.findByOperationId(operationId.longValue()));
    }

    @Override
    @Transactional(readOnly = true)
    public List<OperationTeamDTO> findByUserId(Integer userId) {
        return operationTeamMapper.toDto(operationTeamRepository.findByUserId(userId.longValue()));
    }

    @Override
    public OperationTeamDTO save(OperationTeamDTO operationTeamDTO) {
        OperationTeam operationTeam = operationTeamMapper.toEntity(operationTeamDTO);
        operationTeam = operationTeamRepository.save(operationTeam);
        return operationTeamMapper.toDto(operationTeam);
    }

    @Override
    public OperationTeamDTO update(OperationTeamId id, OperationTeamDTO operationTeamDTO) {
        OperationTeam existingOperationTeam = operationTeamRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("OperationTeam not found with id: " + id));

        // Update fields using MapStruct's partialUpdate
        operationTeamMapper.partialUpdate(operationTeamDTO, existingOperationTeam);

        OperationTeam updatedOperationTeam = operationTeamRepository.save(existingOperationTeam);
        return operationTeamMapper.toDto(updatedOperationTeam);
    }

    @Override
    public void delete(OperationTeamId id) {
        operationTeamRepository.deleteById(id);
    }

    @Override
    public void deleteByOperationId(Integer operationId) {
        Operation operation = new Operation();
        operation.setId(operationId.longValue());
        operationTeamRepository.deleteByOperation(operation);
    }

    @Override
    public void deleteByUserId(Integer userId) {
        User user = new User();
        user.setId(userId.longValue());
        operationTeamRepository.deleteByUser(user);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsById(OperationTeamId id) {
        return operationTeamRepository.existsById(id);
    }
}