package gn.app.compacttechnology.mappers;

import gn.app.compacttechnology.models.dtos.ProductDTO;
import gn.app.compacttechnology.models.entities.Enterprise;
import gn.app.compacttechnology.models.entities.Product;
import org.mapstruct.*;

import java.util.List;

/**
 * Mapper for the entity {@link Product} and its DTO {@link ProductDTO}.
 */
@Mapper(componentModel = "spring", uses = {EnterpriseMapper.class})
public interface ProductMapper {

    @Mapping(target = "enterpriseId", source = "enterprise.id")
    ProductDTO toDto(Product entity);

    @Mapping(target = "enterprise", source = "enterpriseId", qualifiedByName = "enterpriseFromId")
    Product toEntity(ProductDTO dto);

    List<ProductDTO> toDto(List<Product> entityList);
    
    List<Product> toEntity(List<ProductDTO> dtoList);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "enterprise", ignore = true)
    Product partialUpdate(ProductDTO dto, @MappingTarget Product entity);

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