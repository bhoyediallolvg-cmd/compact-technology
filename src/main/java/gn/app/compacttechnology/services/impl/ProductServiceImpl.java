package gn.app.compacttechnology.services.impl;

import gn.app.compacttechnology.mappers.ProductMapper;
import gn.app.compacttechnology.models.dtos.ProductDTO;
import gn.app.compacttechnology.models.entities.Product;
import gn.app.compacttechnology.repositories.ProductRepository;
import gn.app.compacttechnology.services.ProductService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductServiceImpl(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductDTO> findAll() {
        return productMapper.toDto(productRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ProductDTO> findById(Integer id) {
        return productRepository.findById(id.longValue())
                .map(productMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ProductDTO> findByReference(String reference) {
        return productRepository.findByReference(reference)
                .map(productMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductDTO> findByEnterpriseId(Integer enterpriseId) {
        return productMapper.toDto(productRepository.findByEnterpriseId(enterpriseId.longValue()));
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductDTO> findByType(Integer type) {
        return productMapper.toDto(productRepository.findByType(type.toString()));
    }

    @Override
    public ProductDTO save(ProductDTO productDTO) {
        Product product = productMapper.toEntity(productDTO);
        product = productRepository.save(product);
        return productMapper.toDto(product);
    }

    @Override
    public ProductDTO update(Integer id, ProductDTO productDTO) {
        Product existingProduct = productRepository.findById(id.longValue())
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));

        // Update fields using MapStruct's partialUpdate
        productMapper.partialUpdate(productDTO, existingProduct);

        Product updatedProduct = productRepository.save(existingProduct);
        return productMapper.toDto(updatedProduct);
    }

    @Override
    public void delete(Integer id) {
        productRepository.deleteById(id.longValue());
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsById(Integer id) {
        return productRepository.existsById(id.longValue());
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsByReference(String reference) {
        return productRepository.existsByReference(reference);
    }
}