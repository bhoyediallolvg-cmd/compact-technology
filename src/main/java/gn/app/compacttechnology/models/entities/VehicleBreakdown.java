package gn.app.compacttechnology.models.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "vehicle_breakdown")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VehicleBreakdown {
    
    @EmbeddedId
    private VehicleBreakdownId id;
    
    @ManyToOne
    @MapsId("vehicleId")
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;
    
    @ManyToOne
    @MapsId("breakdownId")
    @JoinColumn(name = "breakdown_id")
    private Breakdown breakdown;
    
    @ManyToOne
    @JoinColumn(name = "operation_id")
    private Operation operation;
    
    @Embeddable
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class VehicleBreakdownId implements java.io.Serializable {
        
        private static final long serialVersionUID = 1L;
        
        @Column(name = "vehicle_id")
        private Long vehicleId;
        
        @Column(name = "breakdown_id")
        private Long breakdownId;
    }
}