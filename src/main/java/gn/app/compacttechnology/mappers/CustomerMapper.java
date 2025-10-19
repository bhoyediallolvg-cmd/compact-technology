package gn.app.compacttechnology.mappers;

import gn.app.compacttechnology.models.dtos.CustomerDTO;
import gn.app.compacttechnology.models.entities.Customer;
import gn.app.compacttechnology.models.entities.Enterprise;
import org.mapstruct.*;

import java.util.List;

/**
 * Mapper for the entity {@link Customer} and its DTO {@link CustomerDTO}.
 */
@Mapper(componentModel = "spring", uses = {EnterpriseMapper.class})
public interface CustomerMapper {

    @Mapping(target = "enterpriseId", source = "enterprise.id")
    @Mapping(target = "enterprise", source = "enterprise")
    @Mapping(target = "type", expression = "java(entity.getType() != null ? Integer.valueOf(entity.getType()) : null)")
    CustomerDTO toDto(Customer entity);

    @Mapping(target = "enterprise", source = "enterpriseId", qualifiedByName = "enterpriseFromId")
    @Mapping(target = "type", expression = "java(dto.getType() != null ? dto.getType().toString() : null)")
    Customer toEntity(CustomerDTO dto);

    List<CustomerDTO> toDto(List<Customer> entityList);
    List<Customer> toEntity(List<CustomerDTO> dtoList);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "enterprise", ignore = true)
    Customer partialUpdate(CustomerDTO dto, @MappingTarget Customer entity);

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
