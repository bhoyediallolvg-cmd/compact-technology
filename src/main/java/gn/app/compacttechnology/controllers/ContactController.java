package gn.app.compacttechnology.controllers;

import gn.app.compacttechnology.models.dtos.ContactDTO;
import gn.app.compacttechnology.services.ContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contacts")
@RequiredArgsConstructor
public class ContactController {

    private final ContactService contactService;

    @GetMapping
    public ResponseEntity<List<ContactDTO>> getAllContacts() {
        return ResponseEntity.ok(contactService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContactDTO> getContactById(@PathVariable Integer id) {
        return contactService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/phone/{phone}")
    public ResponseEntity<ContactDTO> getContactByPhone(@PathVariable String phone) {
        return contactService.findByPhone(phone)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<ContactDTO>> getContactsByCustomerId(@PathVariable Integer customerId) {
        return ResponseEntity.ok(contactService.findByCustomerId(customerId));
    }

    @PostMapping
    public ResponseEntity<ContactDTO> createContact(@RequestBody ContactDTO contactDTO) {
        try {
            ContactDTO resultDTO = contactService.save(contactDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(resultDTO);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ContactDTO> updateContact(@PathVariable Integer id, @RequestBody ContactDTO contactDTO) {
        if (!contactService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        try {
            ContactDTO resultDTO = contactService.update(id, contactDTO);
            return ResponseEntity.ok(resultDTO);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContact(@PathVariable Integer id) {
        if (!contactService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        contactService.delete(id);
        return ResponseEntity.noContent().build();
    }
}