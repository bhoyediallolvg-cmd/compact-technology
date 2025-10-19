package gn.app.compacttechnology.mappers;

import gn.app.compacttechnology.models.dtos.VehicleBreakdownDTO;
import gn.app.compacttechnology.models.entities.Breakdown;
import gn.app.compacttechnology.models.entities.Operation;
import gn.app.compacttechnology.models.entities.Vehicle;
import gn.app.compacttechnology.models.entities.VehicleBreakdown;
import gn.app.compacttechnology.models.entities.VehicleBreakdown.VehicleBreakdownId;
import org.mapstruct.*;

import java.util.List;

/**
 * Mapper for the entity {@link VehicleBreakdown} and its DTO {@link VehicleBreakdownDTO}.
 */
@Mapper(componentModel = "spring", uses = {VehicleMapper.class, BreakdownMapper.class, OperationMapper.class})
public interface VehicleBreakdownMapper {

    @Mapping(target = "vehicleId", source = "vehicle.id")
    @Mapping(target = "breakdownId", source = "breakdown.id")
    @Mapping(target = "operationId", source = "operation.id")
    VehicleBreakdownDTO toDto(VehicleBreakdown entity);

    @Mapping(target = "id", source = ".", qualifiedByName = "vehicleBreakdownIdFromDto")
    @Mapping(target = "vehicle", source = "vehicleId", qualifiedByName = "vehicleFromId")
    @Mapping(target = "breakdown", source = "breakdownId", qualifiedByName = "breakdownFromId")
    @Mapping(target = "operation", source = "operationId", qualifiedByName = "operationFromId")
    VehicleBreakdown toEntity(VehicleBreakdownDTO dto);

    List<VehicleBreakdownDTO> toDto(List<VehicleBreakdown> entityList);

    List<VehicleBreakdown> toEntity(List<VehicleBreakdownDTO> dtoList);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "vehicle", ignore = true)
    @Mapping(target = "breakdown", ignore = true)
    @Mapping(target = "operation", source = "operationId", qualifiedByName = "operationFromId")
    VehicleBreakdown partialUpdate(VehicleBreakdownDTO dto, @MappingTarget VehicleBreakdown entity);

    @Named("vehicleBreakdownIdFromDto")
    default VehicleBreakdownId vehicleBreakdownIdFromDto(VehicleBreakdownDTO dto) {
        if (dto.getVehicleId() == null || dto.getBreakdownId() == null) {
            return null;
        }
        return new VehicleBreakdownId(dto.getVehicleId(), dto.getBreakdownId());
    }

//    @Named("vehicleFromId")
//    default Vehicle vehicleFromId(Long id) {
//        if (id == null) {
//            return null;
//        }
//        Vehicle vehicle = new Vehicle();
//        vehicle.setId(id);
//        return vehicle;
//    }

    @Named("breakdownFromId")
    default Breakdown breakdownFromId(Long id) {
        if (id == null) {
            return null;
        }
        Breakdown breakdown = new Breakdown();
        breakdown.setId(id);
        return breakdown;
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
}
