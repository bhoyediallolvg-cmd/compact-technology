package gn.app.compacttechnology.controllers;

import gn.app.compacttechnology.models.dtos.EnterpriseDTO;
import gn.app.compacttechnology.services.EnterpriseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/entreprise")
@RequiredArgsConstructor
public class EntrepriseController {

    private final EnterpriseService enterpriseService;

    @GetMapping
    public ResponseEntity<List<EnterpriseDTO>> getAllEntreprises() {
        return ResponseEntity.ok(enterpriseService.findAll());
    }

    @PostMapping
    public ResponseEntity<EnterpriseDTO> createEnterprise(@RequestBody EnterpriseDTO enterpriseDTO) {
        try {
            EnterpriseDTO resultDTO = enterpriseService.save(enterpriseDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(resultDTO);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<EnterpriseDTO> updateContact(@PathVariable Long id, @RequestBody EnterpriseDTO enterpriseDTO) {
        if (!enterpriseService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        try {
            EnterpriseDTO resultDTO = enterpriseService.update(id, enterpriseDTO);
            return ResponseEntity.ok(resultDTO);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

}
