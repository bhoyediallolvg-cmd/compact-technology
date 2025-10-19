package gn.app.compacttechnology.services;

import gn.app.compacttechnology.models.dtos.JobDTO;

import java.util.List;
import java.util.Optional;

public interface JobService {
    
    List<JobDTO> findAll();
    
    Optional<JobDTO> findById(Integer id);
    
    List<JobDTO> findByEnterpriseId(Integer enterpriseId);
    
    List<JobDTO> findByCategory(Integer category);
    
    List<JobDTO> findByNameContaining(String name);
    
    JobDTO save(JobDTO jobDTO);
    
    JobDTO update(Integer id, JobDTO jobDTO);
    
    void delete(Integer id);
    
    boolean existsById(Integer id);
}