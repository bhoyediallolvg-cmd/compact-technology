package gn.app.compacttechnology.repositories;

import gn.app.compacttechnology.models.entities.Customer;
import gn.app.compacttechnology.models.entities.Enterprise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    
    List<Customer> findByEnterprise(Enterprise enterprise);
    
    List<Customer> findByEnterpriseId(Long enterpriseId);
    
    Optional<Customer> findByCustomerNumber(String customerNumber);
    
    boolean existsByCustomerNumber(String customerNumber);
    
    List<Customer> findByType(String type);
}