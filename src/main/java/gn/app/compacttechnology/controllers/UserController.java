package gn.app.compacttechnology.controllers;

import gn.app.compacttechnology.models.dtos.UserDTO;
import gn.app.compacttechnology.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Integer id) {
        return userService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/registration/{registrationNumber}")
    public ResponseEntity<UserDTO> getUserByRegistrationNumber(@PathVariable String registrationNumber) {
        return userService.findByRegistrationNumber(registrationNumber)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/enterprise/{enterpriseId}")
    public ResponseEntity<List<UserDTO>> getUsersByEnterpriseId(@PathVariable Integer enterpriseId) {
        return ResponseEntity.ok(userService.findByEnterpriseId(enterpriseId));
    }

    @GetMapping("/job/{jobId}")
    public ResponseEntity<List<UserDTO>> getUsersByJobId(@PathVariable Integer jobId) {
        return ResponseEntity.ok(userService.findByJobId(jobId));
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<UserDTO>> getUsersByStatus(@PathVariable Integer status) {
        return ResponseEntity.ok(userService.findByStatus(status));
    }

    @GetMapping("/contract-type/{contractType}")
    public ResponseEntity<List<UserDTO>> getUsersByContractType(@PathVariable Integer contractType) {
        return ResponseEntity.ok(userService.findByContractType(contractType));
    }

    @GetMapping("/search")
    public ResponseEntity<List<UserDTO>> getUsersByName(@RequestParam String name) {
        return ResponseEntity.ok(userService.findByName(name));
    }

    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
        try {
            UserDTO resultDTO = userService.save(userDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(resultDTO);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Integer id, @RequestBody UserDTO userDTO) {
        if (!userService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        try {
            UserDTO resultDTO = userService.update(id, userDTO);
            return ResponseEntity.ok(resultDTO);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer id) {
        if (!userService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}