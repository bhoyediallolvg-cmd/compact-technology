package gn.app.compacttechnology.services.impl;

import gn.app.compacttechnology.mappers.CustomerMapper;
import gn.app.compacttechnology.models.dtos.CustomerDTO;
import gn.app.compacttechnology.models.entities.Customer;
import gn.app.compacttechnology.repositories.CustomerRepository;
import gn.app.compacttechnology.services.CustomerService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<CustomerDTO> findAll() {
        return customerMapper.toDto(customerRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<CustomerDTO> findById(Integer id) {
        return customerRepository.findById(id.longValue())
                .map(customerMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<CustomerDTO> findByCustomerNumber(String customerNumber) {
        return customerRepository.findByCustomerNumber(customerNumber)
                .map(customerMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CustomerDTO> findByEnterpriseId(Integer enterpriseId) {
        return customerMapper.toDto(customerRepository.findByEnterpriseId(enterpriseId.longValue()));
    }

    @Override
    public CustomerDTO save(CustomerDTO customerDTO) {
        Customer customer = customerMapper.toEntity(customerDTO);
        customer = customerRepository.save(customer);
        return customerMapper.toDto(customer);
    }

    @Override
    public CustomerDTO update(Integer id, CustomerDTO customerDTO) {
        Customer existingCustomer = customerRepository.findById(id.longValue())
                .orElseThrow(() -> new RuntimeException("Customer not found with id: " + id));

        // Update fields using MapStruct's partialUpdate
        customerMapper.partialUpdate(customerDTO, existingCustomer);

        Customer updatedCustomer = customerRepository.save(existingCustomer);
        return customerMapper.toDto(updatedCustomer);
    }

    @Override
    public void delete(Integer id) {
        customerRepository.deleteById(id.longValue());
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsById(Integer id) {
        return customerRepository.existsById(id.longValue());
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsByCustomerNumber(String customerNumber) {
        return customerRepository.existsByCustomerNumber(customerNumber);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CustomerDTO> findByType(Integer type) {
        return customerMapper.toDto(customerRepository.findByType(type.toString()));
    }
}