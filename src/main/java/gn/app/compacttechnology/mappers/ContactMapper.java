package gn.app.compacttechnology.mappers;

import gn.app.compacttechnology.models.dtos.ContactDTO;
import gn.app.compacttechnology.models.entities.Contact;
import gn.app.compacttechnology.models.entities.Customer;
import org.mapstruct.*;

import java.util.List;

/**
 * Mapper for the entity {@link Contact} and its DTO {@link ContactDTO}.
 */
@Mapper(componentModel = "spring", uses = {CustomerMapper.class})
public interface ContactMapper {

    @Mapping(target = "customerId", source = "customer.id")
    @Mapping(target = "customer", source = "customer")
    ContactDTO toDto(Contact entity);

    @Mapping(target = "customer", source = "customerId", qualifiedByName = "customerFromId")
    Contact toEntity(ContactDTO dto);

    List<ContactDTO> toDto(List<Contact> entityList);
    List<Contact> toEntity(List<ContactDTO> dtoList);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "customer", ignore = true)
    Contact partialUpdate(ContactDTO dto, @MappingTarget Contact entity);

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
