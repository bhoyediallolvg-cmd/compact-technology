package gn.app.compacttechnology.services.impl;

import gn.app.compacttechnology.exception.RequestException;
import gn.app.compacttechnology.mappers.EnterpriseMapper;
import gn.app.compacttechnology.models.dtos.EnterpriseDTO;
import gn.app.compacttechnology.models.entities.Enterprise;
import gn.app.compacttechnology.repositories.EnterpriseRepository;
import gn.app.compacttechnology.services.EnterpriseService;
import gn.app.compacttechnology.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class EnterpriseServiceImpl implements EnterpriseService {

    private final EnterpriseRepository enterpriseRepository;
    private final EnterpriseMapper enterpriseMapper;
    private final MessageSource messageSource;


    @Override
    @Transactional(readOnly = true)
    public List<EnterpriseDTO> findAll() {
        return enterpriseMapper.toDto(enterpriseRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<EnterpriseDTO> findById(Long id) {
        return enterpriseRepository.findById(id)
                .map(enterpriseMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<EnterpriseDTO> findBySubdomain(String subdomain) {
        return enterpriseRepository.findBySubdomain(subdomain)
                .map(enterpriseMapper::toDto);
    }

    @Override
    public EnterpriseDTO save(EnterpriseDTO enterpriseDTO) {
        enterpriseRepository.findByName(enterpriseDTO.getName()).ifPresent(
                enterprise -> {
                    throw new RequestException(
                            messageSource.getMessage(
                                    "enterprise.exists", new Object[]{enterpriseDTO.getName()},
                                    Locale.getDefault()),
                            HttpStatus.CONFLICT);
                }
        );
        Enterprise enterprise = enterpriseMapper.toEntity(enterpriseDTO);
        enterprise = enterpriseRepository.save(enterprise);
        return enterpriseMapper.toDto(enterprise);
    }

    @Override
    public EnterpriseDTO update(Long id, EnterpriseDTO enterpriseDTO) {
        Enterprise existingEnterprise = enterpriseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Enterprise not found with id: " + id));

        // Update fields using MapStruct's partialUpdate
        enterpriseMapper.partialUpdate(enterpriseDTO, existingEnterprise);

        Enterprise updatedEnterprise = enterpriseRepository.save(existingEnterprise);
        return enterpriseMapper.toDto(updatedEnterprise);
    }

    @Override
    public void delete(Long id) {
        enterpriseRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsById(Long id) {
        return enterpriseRepository.existsById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsBySubdomain(String subdomain) {
        return enterpriseRepository.existsBySubdomain(subdomain);
    }

    // Helper methods for DTO conversion
    private EnterpriseDTO convertToDTO(Enterprise enterprise) {
        EnterpriseDTO dto = new EnterpriseDTO();
        BeanUtils.copyProperties(enterprise, dto);
        return dto;
    }

    private Enterprise convertToEntity(EnterpriseDTO dto) {
        Enterprise entity = new Enterprise();
        BeanUtils.copyProperties(dto, entity);
        return entity;
    }
}
