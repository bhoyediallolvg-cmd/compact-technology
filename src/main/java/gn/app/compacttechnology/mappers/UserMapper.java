package gn.app.compacttechnology.mappers;

import gn.app.compacttechnology.models.dtos.UserDTO;
import gn.app.compacttechnology.models.entities.Enterprise;
import gn.app.compacttechnology.models.entities.Job;
import gn.app.compacttechnology.models.entities.User;
import org.mapstruct.*;

import java.util.List;

/**
 * Mapper for the entity {@link User} and its DTO {@link UserDTO}.
 */
@Mapper(componentModel = "spring", uses = {JobMapper.class, EnterpriseMapper.class})
public interface UserMapper {

    @Mapping(target = "jobId", source = "job.id")
    @Mapping(target = "enterpriseId", source = "enterprise.id")
    UserDTO toDto(User entity);

    @Mapping(target = "job", source = "jobId", qualifiedByName = "jobFromId")
    @Mapping(target = "enterprise", source = "enterpriseId", qualifiedByName = "enterpriseFromId")
    User toEntity(UserDTO dto);

    List<UserDTO> toDto(List<User> entityList);
    
    List<User> toEntity(List<UserDTO> dtoList);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "job", ignore = true)
    @Mapping(target = "enterprise", ignore = true)
    User partialUpdate(UserDTO dto, @MappingTarget User entity);

    @Named("jobFromId")
    default Job jobFromId(Long id) {
        if (id == null) {
            return null;
        }
        Job job = new Job();
        job.setId(id.longValue());
        return job;
    }

//    @Named("enterpriseFromId")
//    default Enterprise enterpriseFromId(Long id) {
//        if (id == null) {
//            return null;
//        }
//        Enterprise enterprise = new Enterprise();
//        enterprise.setId(id);
//        return enterprise;
//    }
}