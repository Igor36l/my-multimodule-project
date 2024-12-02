package org.market.service;

import lombok.RequiredArgsConstructor;
import org.market.controller.dto.ProductCreateEditDto;
import org.market.entity.Product;
import org.market.entity.ProductImage;
import org.market.mapper.ProductMapper;
import org.market.repository.ImageRepository;
import org.market.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ImageRepository imageRepository;

    public Product createProduct(Product product) {
        product.setCreatedAt(LocalDateTime.now());
        product.setUpdatedAt(LocalDateTime.now());
        product.setStock(0);
        return productRepository.save(product);
    }

    public Optional<Product> getProductById(long id) {
        return productRepository.findById(id);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public boolean deleteProductById(long id) {
        Optional<Product> productById = getProductById(id);
        productById.ifPresent(productRepository::delete);
        return productById.isEmpty();
    }

    public Optional<Product> update(Long id, ProductCreateEditDto productDto) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            return Optional.of(productRepository.save(ProductMapper.toProduct(productDto)));
        }
        return Optional.empty();
    }

    public List<ProductImage> getImageForProduct(Long productId) {
        return imageRepository.findByProductId(productId);
    }
}
