package gn.app.compacttechnology.services.impl;

import gn.app.compacttechnology.mappers.BreakdownMapper;
import gn.app.compacttechnology.models.dtos.BreakdownDTO;
import gn.app.compacttechnology.models.entities.Breakdown;
import gn.app.compacttechnology.repositories.BreakdownRepository;
import gn.app.compacttechnology.services.BreakdownService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BreakdownServiceImpl implements BreakdownService {

    private final BreakdownRepository breakdownRepository;
    private final BreakdownMapper breakdownMapper;

    public BreakdownServiceImpl(BreakdownRepository breakdownRepository, BreakdownMapper breakdownMapper) {
        this.breakdownRepository = breakdownRepository;
        this.breakdownMapper = breakdownMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<BreakdownDTO> findAll() {
        return breakdownMapper.toDto(breakdownRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<BreakdownDTO> findById(Integer id) {
        return breakdownRepository.findById(id.longValue())
                .map(breakdownMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<BreakdownDTO> findByType(Integer type) {
        return breakdownMapper.toDto(breakdownRepository.findByType(type));
    }

    @Override
    @Transactional(readOnly = true)
    public List<BreakdownDTO> findByDescriptionContaining(String description) {
        return breakdownMapper.toDto(breakdownRepository.findByDescriptionContainingIgnoreCase(description));
    }

    @Override
    @Transactional(readOnly = true)
    public List<BreakdownDTO> findByBreakedAtBetween(LocalDateTime start, LocalDateTime end) {
        return breakdownMapper.toDto(breakdownRepository.findByBreakedAtBetween(start, end));
    }

    @Override
    @Transactional(readOnly = true)
    public List<BreakdownDTO> findByFixedAtBetween(LocalDateTime start, LocalDateTime end) {
        return breakdownMapper.toDto(breakdownRepository.findByFixedAtBetween(start, end));
    }

    @Override
    @Transactional(readOnly = true)
    public List<BreakdownDTO> findByCreatedAtBetween(LocalDateTime start, LocalDateTime end) {
        return breakdownMapper.toDto(breakdownRepository.findByCreatedAtBetween(start, end));
    }

    @Override
    @Transactional(readOnly = true)
    public List<BreakdownDTO> findByWorkforceCostGreaterThanEqual(Integer minCost) {
        return breakdownMapper.toDto(breakdownRepository.findByWorkforceCostGreaterThanEqual(minCost));
    }

    @Override
    @Transactional(readOnly = true)
    public List<BreakdownDTO> findByPartsCostGreaterThanEqual(Integer minCost) {
        return breakdownMapper.toDto(breakdownRepository.findByPartsCostGreaterThanEqual(minCost));
    }

    @Override
    @Transactional(readOnly = true)
    public List<BreakdownDTO> findByFixedAtIsNull() {
        return breakdownMapper.toDto(breakdownRepository.findByFixedAtIsNull());
    }

    @Override
    public BreakdownDTO save(BreakdownDTO breakdownDTO) {
        Breakdown breakdown = breakdownMapper.toEntity(breakdownDTO);
        breakdown = breakdownRepository.save(breakdown);
        return breakdownMapper.toDto(breakdown);
    }

    @Override
    public BreakdownDTO update(Integer id, BreakdownDTO breakdownDTO) {
        Breakdown existingBreakdown = breakdownRepository.findById(id.longValue())
                .orElseThrow(() -> new RuntimeException("Breakdown not found with id: " + id));

        // Update fields using MapStruct's partialUpdate
        breakdownMapper.partialUpdate(breakdownDTO, existingBreakdown);

        Breakdown updatedBreakdown = breakdownRepository.save(existingBreakdown);
        return breakdownMapper.toDto(updatedBreakdown);
    }

    @Override
    public void delete(Integer id) {
        breakdownRepository.deleteById(id.longValue());
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsById(Integer id) {
        return breakdownRepository.existsById(id.longValue());
    }
}