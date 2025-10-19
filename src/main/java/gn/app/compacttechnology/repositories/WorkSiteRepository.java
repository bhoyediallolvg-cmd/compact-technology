package gn.app.compacttechnology.repositories;

import gn.app.compacttechnology.models.entities.Customer;
import gn.app.compacttechnology.models.entities.WorkSite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WorkSiteRepository extends JpaRepository<WorkSite, Long> {
    
    List<WorkSite> findByCustomer(Customer customer);
    
    List<WorkSite> findByCustomerId(Long customerId);
    
    Optional<WorkSite> findByReference(String reference);
    
    List<WorkSite> findByAddressContainingIgnoreCase(String address);
    
    List<WorkSite> findByReferenceContainingIgnoreCase(String reference);
}