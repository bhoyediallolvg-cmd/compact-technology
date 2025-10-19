package gn.app.compacttechnology.repositories;

import gn.app.compacttechnology.models.entities.Enterprise;
import gn.app.compacttechnology.models.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    
    List<Product> findByEnterprise(Enterprise enterprise);
    
    List<Product> findByEnterpriseId(Long enterpriseId);
    
    Optional<Product> findByReference(String reference);
    
    boolean existsByReference(String reference);
    
    List<Product> findByType(String type);
}