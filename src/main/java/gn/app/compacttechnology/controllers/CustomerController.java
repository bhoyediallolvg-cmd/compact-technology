package gn.app.compacttechnology.controllers;

import gn.app.compacttechnology.models.dtos.CustomerDTO;
import gn.app.compacttechnology.services.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping
    public ResponseEntity<List<CustomerDTO>> getAllCustomers() {
        return ResponseEntity.ok(customerService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDTO> getCustomerById(@PathVariable Integer id) {
        return customerService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/number/{customerNumber}")
    public ResponseEntity<CustomerDTO> getCustomerByNumber(@PathVariable String customerNumber) {
        return customerService.findByCustomerNumber(customerNumber)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/enterprise/{enterpriseId}")
    public ResponseEntity<List<CustomerDTO>> getCustomersByEnterpriseId(@PathVariable Integer enterpriseId) {
        return ResponseEntity.ok(customerService.findByEnterpriseId(enterpriseId));
    }

    @GetMapping("/type/{type}")
    public ResponseEntity<List<CustomerDTO>> getCustomersByType(@PathVariable Integer type) {
        return ResponseEntity.ok(customerService.findByType(type));
    }

    @PostMapping
    public ResponseEntity<CustomerDTO> createCustomer(@RequestBody CustomerDTO customerDTO) {
        try {
            CustomerDTO resultDTO = customerService.save(customerDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(resultDTO);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerDTO> updateCustomer(@PathVariable Integer id, @RequestBody CustomerDTO customerDTO) {
        if (!customerService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        try {
            CustomerDTO resultDTO = customerService.update(id, customerDTO);
            return ResponseEntity.ok(resultDTO);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Integer id) {
        if (!customerService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        customerService.delete(id);
        return ResponseEntity.noContent().build();
    }
}