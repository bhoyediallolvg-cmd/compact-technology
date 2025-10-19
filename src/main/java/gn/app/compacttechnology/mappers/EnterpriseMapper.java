package gn.app.compacttechnology.mappers;

import gn.app.compacttechnology.models.dtos.EnterpriseDTO;
import gn.app.compacttechnology.models.entities.Enterprise;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

/**
 * Mapper for the entity {@link Enterprise} and its DTO {@link EnterpriseDTO}.
 */
@Mapper(componentModel = "spring")
public interface EnterpriseMapper {
    EnterpriseDTO toDto(Enterprise entity);
    Enterprise toEntity(EnterpriseDTO dto);

    List<EnterpriseDTO> toDto(List<Enterprise> entityList);
    List<Enterprise> toEntity(List<EnterpriseDTO> dtoList);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Enterprise partialUpdate(EnterpriseDTO dto, @MappingTarget Enterprise entity);
}
