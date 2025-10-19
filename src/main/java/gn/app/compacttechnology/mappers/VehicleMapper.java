package gn.app.compacttechnology.mappers;

import gn.app.compacttechnology.models.dtos.VehicleDTO;
import gn.app.compacttechnology.models.entities.Enterprise;
import gn.app.compacttechnology.models.entities.Vehicle;
import org.mapstruct.*;

import java.util.List;

/**
 * Mapper for the entity {@link Vehicle} and its DTO {@link VehicleDTO}.
 */
@Mapper(componentModel = "spring", uses = {EnterpriseMapper.class})
public interface VehicleMapper {

    @Mapping(target = "enterpriseId", source = "enterprise.id")
    @Mapping(target = "type", expression = "java(entity.getType() != null ? Integer.valueOf(entity.getType()) : null)")
    VehicleDTO toDto(Vehicle entity);

    @Mapping(target = "enterprise", source = "enterpriseId", qualifiedByName = "enterpriseFromId")
    @Mapping(target = "type", expression = "java(dto.getType() != null ? dto.getType().toString() : null)")
    Vehicle toEntity(VehicleDTO dto);

    List<VehicleDTO> toDto(List<Vehicle> entityList);
    
    List<Vehicle> toEntity(List<VehicleDTO> dtoList);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "enterprise", ignore = true)
    @Mapping(target = "type", expression = "java(dto.getType() != null ? dto.getType().toString() : entity.getType())")
    Vehicle partialUpdate(VehicleDTO dto, @MappingTarget Vehicle entity);

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