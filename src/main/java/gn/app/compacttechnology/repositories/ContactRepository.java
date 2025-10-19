package gn.app.compacttechnology.repositories;

import gn.app.compacttechnology.models.entities.Contact;
import gn.app.compacttechnology.models.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {
    
    List<Contact> findByCustomer(Customer customer);
    
    List<Contact> findByCustomerId(Integer customerId);
    
    Optional<Contact> findByPhone(String phone);
    
    boolean existsByPhone(String phone);
}