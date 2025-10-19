package gn.app.compacttechnology.repositories;

import gn.app.compacttechnology.models.entities.Enterprise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EnterpriseRepository extends JpaRepository<Enterprise, Long> {
    
    Optional<Enterprise> findBySubdomain(String subdomain);
    Optional<Enterprise> findByName(String subdomain);

    boolean existsBySubdomain(String subdomain);
}