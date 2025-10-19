package gn.app.compacttechnology.repositories;

import gn.app.compacttechnology.models.entities.Enterprise;
import gn.app.compacttechnology.models.entities.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobRepository extends JpaRepository<Job, Long> {
    
    List<Job> findByEnterprise(Enterprise enterprise);
    
    List<Job> findByEnterpriseId(Long enterpriseId);
    
    List<Job> findByCategory(String category);
    
    List<Job> findByNameContainingIgnoreCase(String name);
}