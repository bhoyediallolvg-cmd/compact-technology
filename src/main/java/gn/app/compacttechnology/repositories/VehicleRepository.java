package gn.app.compacttechnology.repositories;

import gn.app.compacttechnology.models.entities.Enterprise;
import gn.app.compacttechnology.models.entities.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    
    List<Vehicle> findByEnterprise(Enterprise enterprise);
    
    List<Vehicle> findByEnterpriseId(Long enterpriseId);
    
    Optional<Vehicle> findByRegistrationNumber(String registrationNumber);
    
    boolean existsByRegistrationNumber(String registrationNumber);
    
    List<Vehicle> findByStatus(Integer status);
    
    List<Vehicle> findByType(String type);
}