package gn.app.compacttechnology.services;

import gn.app.compacttechnology.models.dtos.ProductDTO;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    
    List<ProductDTO> findAll();
    
    Optional<ProductDTO> findById(Integer id);
    
    Optional<ProductDTO> findByReference(String reference);
    
    List<ProductDTO> findByEnterpriseId(Integer enterpriseId);
    
    List<ProductDTO> findByType(Integer type);
    
    ProductDTO save(ProductDTO productDTO);
    
    ProductDTO update(Integer id, ProductDTO productDTO);
    
    void delete(Integer id);
    
    boolean existsById(Integer id);
    
    boolean existsByReference(String reference);
}