package gn.app.compacttechnology.services.impl;

import gn.app.compacttechnology.mappers.UserMapper;
import gn.app.compacttechnology.models.dtos.UserDTO;
import gn.app.compacttechnology.models.entities.User;
import gn.app.compacttechnology.repositories.UserRepository;
import gn.app.compacttechnology.services.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserDTO> findAll() {
        return userMapper.toDto(userRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<UserDTO> findById(Integer id) {
        return userRepository.findById(id.longValue())
                .map(userMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<UserDTO> findByRegistrationNumber(String registrationNumber) {
        return userRepository.findByRegistrationNumber(registrationNumber)
                .map(userMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserDTO> findByEnterpriseId(Integer enterpriseId) {
        return userMapper.toDto(userRepository.findByEnterpriseId(enterpriseId.longValue()));
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserDTO> findByJobId(Integer jobId) {
        return userMapper.toDto(userRepository.findByJobId(jobId.longValue()));
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserDTO> findByStatus(Integer status) {
        return userMapper.toDto(userRepository.findByStatus(status));
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserDTO> findByContractType(Integer contractType) {
        return userMapper.toDto(userRepository.findByContractType(contractType));
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserDTO> findByName(String name) {
        return userMapper.toDto(userRepository.findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(name, name));
    }

    @Override
    public UserDTO save(UserDTO userDTO) {
        User user = userMapper.toEntity(userDTO);
        user = userRepository.save(user);
        return userMapper.toDto(user);
    }

    @Override
    public UserDTO update(Integer id, UserDTO userDTO) {
        User existingUser = userRepository.findById(id.longValue())
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));

        // Update fields using MapStruct's partialUpdate
        userMapper.partialUpdate(userDTO, existingUser);

        User updatedUser = userRepository.save(existingUser);
        return userMapper.toDto(updatedUser);
    }

    @Override
    public void delete(Integer id) {
        userRepository.deleteById(id.longValue());
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsById(Integer id) {
        return userRepository.existsById(id.longValue());
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsByRegistrationNumber(String registrationNumber) {
        return userRepository.existsByRegistrationNumber(registrationNumber);
    }
}