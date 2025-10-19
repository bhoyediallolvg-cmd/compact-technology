package gn.app.compacttechnology.mappers;

import gn.app.compacttechnology.models.dtos.BreakdownDTO;
import gn.app.compacttechnology.models.entities.Breakdown;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

/**
 * Mapper for the entity {@link Breakdown} and its DTO {@link BreakdownDTO}.
 */
@Mapper(componentModel = "spring")
public interface BreakdownMapper {
    BreakdownDTO toDto(Breakdown entity);
    Breakdown toEntity(BreakdownDTO dto);

    List<BreakdownDTO> toDto(List<Breakdown> entityList);
    List<Breakdown> toEntity(List<BreakdownDTO> dtoList);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Breakdown partialUpdate(BreakdownDTO dto, @MappingTarget Breakdown entity);
}