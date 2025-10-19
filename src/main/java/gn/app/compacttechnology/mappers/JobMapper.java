package gn.app.compacttechnology.mappers;

import gn.app.compacttechnology.models.dtos.JobDTO;
import gn.app.compacttechnology.models.entities.Enterprise;
import gn.app.compacttechnology.models.entities.Job;
import org.mapstruct.*;

import java.util.List;

/**
 * Mapper for the entity {@link Job} and its DTO {@link JobDTO}.
 */
@Mapper(componentModel = "spring", uses = {EnterpriseMapper.class})
public interface JobMapper {

    @Mapping(target = "enterpriseId", source = "enterprise.id")
    JobDTO toDto(Job entity);

    @Mapping(target = "enterprise", source = "enterpriseId", qualifiedByName = "enterpriseFromId")
    @Mapping(target = "category", source = "category", qualifiedByName = "categoryToString")
    Job toEntity(JobDTO dto);

    List<JobDTO> toDto(List<Job> entityList);
    
    List<Job> toEntity(List<JobDTO> dtoList);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "enterprise", ignore = true)
    Job partialUpdate(JobDTO dto, @MappingTarget Job entity);

    @Named("enterpriseFromId")
    default Enterprise enterpriseFromId(Long id) {
        if (id == null) {
            return null;
        }
        Enterprise enterprise = new Enterprise();
        enterprise.setId(id);
        return enterprise;
    }
    
    @Named("categoryToString")
    default String categoryToString(Integer category) {
        if (category == null) {
            return null;
        }
        return category.toString();
    }
}