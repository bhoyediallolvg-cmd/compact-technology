package gn.app.compacttechnology.controllers;

import gn.app.compacttechnology.models.dtos.JobDTO;
import gn.app.compacttechnology.services.JobService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jobs")
@RequiredArgsConstructor
public class JobController {

    private final JobService jobService;

    @GetMapping
    public ResponseEntity<List<JobDTO>> getAllJobs() {
        return ResponseEntity.ok(jobService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<JobDTO> getJobById(@PathVariable Integer id) {
        return jobService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/enterprise/{enterpriseId}")
    public ResponseEntity<List<JobDTO>> getJobsByEnterpriseId(@PathVariable Integer enterpriseId) {
        return ResponseEntity.ok(jobService.findByEnterpriseId(enterpriseId));
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<JobDTO>> getJobsByCategory(@PathVariable Integer category) {
        return ResponseEntity.ok(jobService.findByCategory(category));
    }

    @GetMapping("/search")
    public ResponseEntity<List<JobDTO>> getJobsByName(@RequestParam String name) {
        return ResponseEntity.ok(jobService.findByNameContaining(name));
    }

    @PostMapping
    public ResponseEntity<JobDTO> createJob(@RequestBody JobDTO jobDTO) {
        try {
            JobDTO resultDTO = jobService.save(jobDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(resultDTO);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<JobDTO> updateJob(@PathVariable Integer id, @RequestBody JobDTO jobDTO) {
        if (!jobService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        try {
            JobDTO resultDTO = jobService.update(id, jobDTO);
            return ResponseEntity.ok(resultDTO);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteJob(@PathVariable Integer id) {
        if (!jobService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        jobService.delete(id);
        return ResponseEntity.noContent().build();
    }
}