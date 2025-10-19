package gn.app.compacttechnology.services.impl;

import gn.app.compacttechnology.mappers.JobMapper;
import gn.app.compacttechnology.models.dtos.JobDTO;
import gn.app.compacttechnology.models.entities.Job;
import gn.app.compacttechnology.repositories.JobRepository;
import gn.app.compacttechnology.services.JobService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class JobServiceImpl implements JobService {

    private final JobRepository jobRepository;
    private final JobMapper jobMapper;

    public JobServiceImpl(JobRepository jobRepository, JobMapper jobMapper) {
        this.jobRepository = jobRepository;
        this.jobMapper = jobMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<JobDTO> findAll() {
        return jobMapper.toDto(jobRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<JobDTO> findById(Integer id) {
        return jobRepository.findById(id.longValue())
                .map(jobMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<JobDTO> findByEnterpriseId(Integer enterpriseId) {
        return jobMapper.toDto(jobRepository.findByEnterpriseId(enterpriseId.longValue()));
    }

    @Override
    @Transactional(readOnly = true)
    public List<JobDTO> findByCategory(Integer category) {
        return jobMapper.toDto(jobRepository.findByCategory(category.toString()));
    }

    @Override
    @Transactional(readOnly = true)
    public List<JobDTO> findByNameContaining(String name) {
        return jobMapper.toDto(jobRepository.findByNameContainingIgnoreCase(name));
    }

    @Override
    public JobDTO save(JobDTO jobDTO) {
        Job job = jobMapper.toEntity(jobDTO);
        job = jobRepository.save(job);
        return jobMapper.toDto(job);
    }

    @Override
    public JobDTO update(Integer id, JobDTO jobDTO) {
        Job existingJob = jobRepository.findById(id.longValue())
                .orElseThrow(() -> new RuntimeException("Job not found with id: " + id));

        // Update fields using MapStruct's partialUpdate
        jobMapper.partialUpdate(jobDTO, existingJob);

        Job updatedJob = jobRepository.save(existingJob);
        return jobMapper.toDto(updatedJob);
    }

    @Override
    public void delete(Integer id) {
        jobRepository.deleteById(id.longValue());
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsById(Integer id) {
        return jobRepository.existsById(id.longValue());
    }
}