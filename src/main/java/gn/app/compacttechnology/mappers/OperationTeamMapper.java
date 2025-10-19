package gn.app.compacttechnology.mappers;

import gn.app.compacttechnology.models.dtos.OperationTeamDTO;
import gn.app.compacttechnology.models.entities.Operation;
import gn.app.compacttechnology.models.entities.OperationTeam;
import gn.app.compacttechnology.models.entities.User;
import org.mapstruct.*;

import java.util.List;

/**
 * Mapper for the entity {@link OperationTeam} and its DTO {@link OperationTeamDTO}.
 */
@Mapper(componentModel = "spring", uses = {OperationMapper.class, UserMapper.class})
public interface OperationTeamMapper {

    @Mapping(target = "operationId", source = "operation.id")
    @Mapping(target = "userId", source = "user.id")
    OperationTeamDTO toDto(OperationTeam entity);

    @Mapping(target = "id", source = ".", qualifiedByName = "operationTeamIdFromDto")
    @Mapping(target = "operation", source = "operationId", qualifiedByName = "operationFromId")
    @Mapping(target = "user", source = "userId", qualifiedByName = "userFromId")
    OperationTeam toEntity(OperationTeamDTO dto);

    List<OperationTeamDTO> toDto(List<OperationTeam> entityList);
    
    List<OperationTeam> toEntity(List<OperationTeamDTO> dtoList);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "operation", ignore = true)
    @Mapping(target = "user", ignore = true)
    OperationTeam partialUpdate(OperationTeamDTO dto, @MappingTarget OperationTeam entity);

    @Named("operationTeamIdFromDto")
    default OperationTeam.OperationTeamId operationTeamIdFromDto(OperationTeamDTO dto) {
        if (dto.getOperationId() == null || dto.getUserId() == null) {
            return null;
        }
        return new OperationTeam.OperationTeamId(dto.getOperationId(), dto.getUserId());
    }

    @Named("operationFromId")
    default Operation operationFromId(Long id) {
        if (id == null) {
            return null;
        }
        Operation operation = new Operation();
        operation.setId(id);
        return operation;
    }

//    @Named("userFromId")
//    default User userFromId(Long id) {
//        if (id == null) {
//            return null;
//        }
//        User user = new User();
//        user.setId(id);
//        return user;
//    }
}