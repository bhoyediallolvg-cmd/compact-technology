package gn.app.compacttechnology.mappers;

import gn.app.compacttechnology.models.dtos.ServiceDTO;
import gn.app.compacttechnology.models.entities.Enterprise;
import gn.app.compacttechnology.models.entities.Service;
import org.mapstruct.*;

import java.util.List;

/**
 * Mapper for the entity {@link Service} and its DTO {@link ServiceDTO}.
 */
@Mapper(componentModel = "spring", uses = {EnterpriseMapper.class})
public interface ServiceMapper {

    @Mapping(target = "enterpriseId", source = "enterprise.id")
    ServiceDTO toDto(Service entity);

    @Mapping(target = "enterprise", source = "enterpriseId", qualifiedByName = "enterpriseFromId")
    Service toEntity(ServiceDTO dto);

    List<ServiceDTO> toDto(List<Service> entityList);
    
    List<Service> toEntity(List<ServiceDTO> dtoList);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "enterprise", ignore = true)
    Service partialUpdate(ServiceDTO dto, @MappingTarget Service entity);

    @Named("enterpriseFromId")
    default Enterprise enterpriseFromId(Long id) {
        if (id == null) {
            return null;
        }
        Enterprise enterprise = new Enterprise();
        enterprise.setId(id);
        return enterprise;
    }
}