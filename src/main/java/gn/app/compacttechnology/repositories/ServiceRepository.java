package gn.app.compacttechnology.repositories;

import gn.app.compacttechnology.models.entities.Enterprise;
import gn.app.compacttechnology.models.entities.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ServiceRepository extends JpaRepository<Service, Long> {
    
    List<Service> findByEnterprise(Enterprise enterprise);
    
    List<Service> findByEnterpriseId(Long enterpriseId);
    
    Optional<Service> findByReference(String reference);
    
    boolean existsByReference(String reference);
    
    List<Service> findByDescriptionContainingIgnoreCase(String description);
    
    List<Service> findByPriceLessThanEqual(Double maxPrice);
    
    List<Service> findByPriceGreaterThanEqual(Double minPrice);
}