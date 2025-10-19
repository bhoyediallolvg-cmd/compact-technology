package gn.app.compacttechnology.services.impl;

import gn.app.compacttechnology.mappers.ContactMapper;
import gn.app.compacttechnology.models.dtos.ContactDTO;
import gn.app.compacttechnology.models.entities.Contact;
import gn.app.compacttechnology.repositories.ContactRepository;
import gn.app.compacttechnology.services.ContactService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ContactServiceImpl implements ContactService {

    private final ContactRepository contactRepository;
    private final ContactMapper contactMapper;

    public ContactServiceImpl(ContactRepository contactRepository, ContactMapper contactMapper) {
        this.contactRepository = contactRepository;
        this.contactMapper = contactMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ContactDTO> findAll() {
        return contactMapper.toDto(contactRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ContactDTO> findById(Integer id) {
        return contactRepository.findById(id.longValue())
                .map(contactMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ContactDTO> findByPhone(String phone) {
        return contactRepository.findByPhone(phone)
                .map(contactMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ContactDTO> findByCustomerId(Integer customerId) {
        return contactMapper.toDto(contactRepository.findByCustomerId(customerId));
    }

    @Override
    public ContactDTO save(ContactDTO contactDTO) {
        Contact contact = contactMapper.toEntity(contactDTO);
        contact = contactRepository.save(contact);
        return contactMapper.toDto(contact);
    }

    @Override
    public ContactDTO update(Integer id, ContactDTO contactDTO) {
        Contact existingContact = contactRepository.findById(id.longValue())
                .orElseThrow(() -> new RuntimeException("Contact not found with id: " + id));

        // Update fields using MapStruct's partialUpdate
        contactMapper.partialUpdate(contactDTO, existingContact);

        Contact updatedContact = contactRepository.save(existingContact);
        return contactMapper.toDto(updatedContact);
    }

    @Override
    public void delete(Integer id) {
        contactRepository.deleteById(id.longValue());
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsById(Integer id) {
        return contactRepository.existsById(id.longValue());
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsByPhone(String phone) {
        return contactRepository.existsByPhone(phone);
    }
}