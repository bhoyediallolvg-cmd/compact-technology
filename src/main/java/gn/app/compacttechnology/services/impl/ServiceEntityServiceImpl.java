package gn.app.compacttechnology.services.impl;

import gn.app.compacttechnology.mappers.ServiceMapper;
import gn.app.compacttechnology.models.dtos.ServiceDTO;
import gn.app.compacttechnology.models.entities.Service;
import gn.app.compacttechnology.repositories.ServiceRepository;
import gn.app.compacttechnology.services.ServiceEntityService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Service
@Transactional
public class ServiceEntityServiceImpl implements ServiceEntityService {

    private final ServiceRepository serviceRepository;
    private final ServiceMapper serviceMapper;

    public ServiceEntityServiceImpl(ServiceRepository serviceRepository, ServiceMapper serviceMapper) {
        this.serviceRepository = serviceRepository;
        this.serviceMapper = serviceMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ServiceDTO> findAll() {
        return serviceMapper.toDto(serviceRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ServiceDTO> findById(Integer id) {
        return serviceRepository.findById(id.longValue())
                .map(serviceMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ServiceDTO> findByReference(String reference) {
        return serviceRepository.findByReference(reference)
                .map(serviceMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ServiceDTO> findByEnterpriseId(Integer enterpriseId) {
        return serviceMapper.toDto(serviceRepository.findByEnterpriseId(enterpriseId.longValue()));
    }

    @Override
    @Transactional(readOnly = true)
    public List<ServiceDTO> findByDescriptionContaining(String description) {
        return serviceMapper.toDto(serviceRepository.findByDescriptionContainingIgnoreCase(description));
    }

    @Override
    @Transactional(readOnly = true)
    public List<ServiceDTO> findByPriceLessThanEqual(Integer maxPrice) {
        return serviceMapper.toDto(serviceRepository.findByPriceLessThanEqual(maxPrice.doubleValue()));
    }

    @Override
    @Transactional(readOnly = true)
    public List<ServiceDTO> findByPriceGreaterThanEqual(Integer minPrice) {
        return serviceMapper.toDto(serviceRepository.findByPriceGreaterThanEqual(minPrice.doubleValue()));
    }

    @Override
    public ServiceDTO save(ServiceDTO serviceDTO) {
        Service service = serviceMapper.toEntity(serviceDTO);
        service = serviceRepository.save(service);
        return serviceMapper.toDto(service);
    }

    @Override
    public ServiceDTO update(Integer id, ServiceDTO serviceDTO) {
        Service existingService = serviceRepository.findById(id.longValue())
                .orElseThrow(() -> new RuntimeException("Service not found with id: " + id));

        // Update fields using MapStruct's partialUpdate
        serviceMapper.partialUpdate(serviceDTO, existingService);

        Service updatedService = serviceRepository.save(existingService);
        return serviceMapper.toDto(updatedService);
    }

    @Override
    public void delete(Integer id) {
        serviceRepository.deleteById(id.longValue());
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsById(Integer id) {
        return serviceRepository.existsById(id.longValue());
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsByReference(String reference) {
        return serviceRepository.existsByReference(reference);
    }
}
