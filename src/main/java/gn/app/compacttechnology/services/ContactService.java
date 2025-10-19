package gn.app.compacttechnology.services;

import gn.app.compacttechnology.models.dtos.ContactDTO;

import java.util.List;
import java.util.Optional;

public interface ContactService {
    
    List<ContactDTO> findAll();
    
    Optional<ContactDTO> findById(Integer id);
    
    Optional<ContactDTO> findByPhone(String phone);
    
    List<ContactDTO> findByCustomerId(Integer customerId);
    
    ContactDTO save(ContactDTO contactDTO);
    
    ContactDTO update(Integer id, ContactDTO contactDTO);
    
    void delete(Integer id);
    
    boolean existsById(Integer id);
    
    boolean existsByPhone(String phone);
}