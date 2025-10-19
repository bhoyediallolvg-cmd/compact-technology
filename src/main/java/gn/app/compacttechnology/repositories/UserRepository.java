package gn.app.compacttechnology.repositories;

import gn.app.compacttechnology.models.entities.Enterprise;
import gn.app.compacttechnology.models.entities.Job;
import gn.app.compacttechnology.models.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
    List<User> findByEnterprise(Enterprise enterprise);
    
    List<User> findByEnterpriseId(Long enterpriseId);
    
    List<User> findByJob(Job job);
    
    List<User> findByJobId(Long jobId);
    
    Optional<User> findByRegistrationNumber(String registrationNumber);
    
    boolean existsByRegistrationNumber(String registrationNumber);
    
    List<User> findByStatus(Integer status);
    
    List<User> findByContractType(Integer contractType);
    
    List<User> findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(String firstName, String lastName);
}