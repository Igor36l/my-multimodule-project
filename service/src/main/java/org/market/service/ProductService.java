package org.market.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.market.controller.dto.ProductCreateEditDto;
import org.market.controller.dto.ProductReadDto;
import org.market.entity.Product;
import org.market.entity.ProductImage;
import org.market.mapper.ProductCreateEditMapper;
import org.market.mapper.ProductReadMapper;
import org.market.repository.ImageRepository;
import org.market.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductService {

    private final ProductRepository productRepository;
    private final ImageRepository imageRepository;
    private final ProductCreateEditMapper productCreateEditMapper;
    private final ProductReadMapper productReadMapper;

    public Optional<ProductReadDto> findById(long id) {
        return productRepository.findById(id).map(productReadMapper::map);
    }

    public List<ProductReadDto> findAll() {
        return productRepository.findAll().stream().map(productReadMapper::map).toList();
    }

    @Transactional
    public ProductReadDto create(ProductCreateEditDto dto) {
        Product product = productCreateEditMapper.map(dto);
        return productReadMapper.map(productRepository.save(product));
    }

    @Transactional
    public boolean delete(long id) {
        Optional<Product> productById = productRepository.findById(id);
        List<ProductImage> imageForProduct = getImageForProduct(id);
        imageRepository.deleteAll(imageForProduct);
        productById.ifPresent(productRepository::delete);
        return productById.isEmpty();
    }

    @Transactional
    public void update(Long productId, ProductCreateEditDto productDto) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException("Product not found"));
        productRepository.save(productCreateEditMapper.map(productDto, product));
//        return Optional.of(productReadMapper.map(product));
    }

    public List<ProductImage> getImageForProduct(Long productId) {
        return imageRepository.findByProductId(productId);
    }
}
