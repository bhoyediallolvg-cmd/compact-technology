package gn.app.compacttechnology.mappers;

import gn.app.compacttechnology.models.dtos.WorkSiteDTO;
import gn.app.compacttechnology.models.entities.Customer;
import gn.app.compacttechnology.models.entities.WorkSite;
import org.mapstruct.*;

import java.util.List;

/**
 * Mapper for the entity {@link WorkSite} and its DTO {@link WorkSiteDTO}.
 */
@Mapper(componentModel = "spring", uses = {CustomerMapper.class})
public interface WorkSiteMapper {

    @Mapping(target = "customerId", source = "customer.id")
    @Mapping(target = "customer", source = "customer")
    WorkSiteDTO toDto(WorkSite entity);

    @Mapping(target = "customer", source = "customerId", qualifiedByName = "customerFromId")
    WorkSite toEntity(WorkSiteDTO dto);

    List<WorkSiteDTO> toDto(List<WorkSite> entityList);
    
    List<WorkSite> toEntity(List<WorkSiteDTO> dtoList);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "customer", ignore = true)
    WorkSite partialUpdate(WorkSiteDTO dto, @MappingTarget WorkSite entity);

    @Named("customerFromId")
    default Customer customerFromId(Long id) {
        if (id == null) {
            return null;
        }
        Customer customer = new Customer();
        customer.setId(id);
        return customer;
    }
}