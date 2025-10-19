package gn.app.compacttechnology.mappers;

import gn.app.compacttechnology.models.dtos.OperationDTO;
import gn.app.compacttechnology.models.entities.Operation;
import gn.app.compacttechnology.models.entities.WorkSite;
import gn.app.compacttechnology.models.entities.Service;
import gn.app.compacttechnology.models.entities.Customer;
import gn.app.compacttechnology.models.entities.Vehicle;
import gn.app.compacttechnology.models.entities.User;
import org.mapstruct.*;

import java.util.List;

/**
 * Mapper for the entity {@link Operation} and its DTO {@link OperationDTO}.
 */
@Mapper(componentModel = "spring", uses = {CustomerMapper.class})
public interface OperationMapper {

    @Mapping(target = "workSiteId", source = "workSite.id")
    @Mapping(target = "serviceId", source = "service.id")
    @Mapping(target = "customerId", source = "customer.id")
    @Mapping(target = "vehicleId", source = "vehicle.id")
    @Mapping(target = "teamLeadId", source = "teamLead.id")
    OperationDTO toDto(Operation entity);

    @Mapping(target = "workSite", source = "workSiteId", qualifiedByName = "workSiteFromId")
    @Mapping(target = "service", source = "serviceId", qualifiedByName = "serviceFromId")
    @Mapping(target = "customer", source = "customerId", qualifiedByName = "customerFromId")
    @Mapping(target = "vehicle", source = "vehicleId", qualifiedByName = "vehicleFromId")
    @Mapping(target = "teamLead", source = "teamLeadId", qualifiedByName = "userFromId")
    Operation toEntity(OperationDTO dto);

    List<OperationDTO> toDto(List<Operation> entityList);
    List<Operation> toEntity(List<OperationDTO> dtoList);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "workSite", ignore = true)
    @Mapping(target = "service", ignore = true)
    @Mapping(target = "customer", ignore = true)
    @Mapping(target = "vehicle", ignore = true)
    @Mapping(target = "teamLead", ignore = true)
    Operation partialUpdate(OperationDTO dto, @MappingTarget Operation entity);

    @Named("workSiteFromId")
    default WorkSite workSiteFromId(Long id) {
        if (id == null) {
            return null;
        }
        WorkSite workSite = new WorkSite();
        workSite.setId(id);
        return workSite;
    }

    @Named("serviceFromId")
    default Service serviceFromId(Long id) {
        if (id == null) {
            return null;
        }
        Service service = new Service();
        service.setId(id);
        return service;
    }

    @Named("customerFromId")
    default Customer customerFromId(Long id) {
        if (id == null) {
            return null;
        }
        Customer customer = new Customer();
        customer.setId(id);
        return customer;
    }

    @Named("vehicleFromId")
    default Vehicle vehicleFromId(Long id) {
        if (id == null) {
            return null;
        }
        Vehicle vehicle = new Vehicle();
        vehicle.setId(id);
        return vehicle;
    }

    @Named("userFromId")
    default User userFromId(Long id) {
        if (id == null) {
            return null;
        }
        User user = new User();
        user.setId(id);
        return user;
    }
}
