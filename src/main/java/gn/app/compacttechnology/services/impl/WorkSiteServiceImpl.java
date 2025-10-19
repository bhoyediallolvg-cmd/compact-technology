package gn.app.compacttechnology.services.impl;

import gn.app.compacttechnology.mappers.WorkSiteMapper;
import gn.app.compacttechnology.models.dtos.WorkSiteDTO;
import gn.app.compacttechnology.models.entities.WorkSite;
import gn.app.compacttechnology.repositories.WorkSiteRepository;
import gn.app.compacttechnology.services.WorkSiteService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class WorkSiteServiceImpl implements WorkSiteService {

    private final WorkSiteRepository workSiteRepository;
    private final WorkSiteMapper workSiteMapper;

    public WorkSiteServiceImpl(WorkSiteRepository workSiteRepository, WorkSiteMapper workSiteMapper) {
        this.workSiteRepository = workSiteRepository;
        this.workSiteMapper = workSiteMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<WorkSiteDTO> findAll() {
        return workSiteMapper.toDto(workSiteRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<WorkSiteDTO> findById(Integer id) {
        return workSiteRepository.findById(id.longValue())
                .map(workSiteMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<WorkSiteDTO> findByReference(String reference) {
        return workSiteRepository.findByReference(reference)
                .map(workSiteMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<WorkSiteDTO> findByCustomerId(Integer customerId) {
        return workSiteMapper.toDto(workSiteRepository.findByCustomerId(customerId.longValue()));
    }

    @Override
    @Transactional(readOnly = true)
    public List<WorkSiteDTO> findByAddressContaining(String address) {
        return workSiteMapper.toDto(workSiteRepository.findByAddressContainingIgnoreCase(address));
    }

    @Override
    @Transactional(readOnly = true)
    public List<WorkSiteDTO> findByReferenceContaining(String reference) {
        return workSiteMapper.toDto(workSiteRepository.findByReferenceContainingIgnoreCase(reference));
    }

    @Override
    public WorkSiteDTO save(WorkSiteDTO workSiteDTO) {
        WorkSite workSite = workSiteMapper.toEntity(workSiteDTO);
        workSite = workSiteRepository.save(workSite);
        return workSiteMapper.toDto(workSite);
    }

    @Override
    public WorkSiteDTO update(Integer id, WorkSiteDTO workSiteDTO) {
        WorkSite existingWorkSite = workSiteRepository.findById(id.longValue())
                .orElseThrow(() -> new RuntimeException("WorkSite not found with id: " + id));

        // Update fields using MapStruct's partialUpdate
        workSiteMapper.partialUpdate(workSiteDTO, existingWorkSite);

        WorkSite updatedWorkSite = workSiteRepository.save(existingWorkSite);
        return workSiteMapper.toDto(updatedWorkSite);
    }

    @Override
    public void delete(Integer id) {
        workSiteRepository.deleteById(id.longValue());
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsById(Integer id) {
        return workSiteRepository.existsById(id.longValue());
    }
}