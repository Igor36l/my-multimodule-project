package org.market.service;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.market.entity.Product;
import org.market.entity.ProductImage;
import org.market.repository.ImageRepository;
import org.market.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.nio.file.StandardOpenOption.CREATE;
import static java.nio.file.StandardOpenOption.TRUNCATE_EXISTING;

@Service
@RequiredArgsConstructor
public class ProductImageService {

    @Value("${app.image.bucket}")
    private String bucket;

    private final ProductRepository productRepository;
    private final ImageRepository imageRepository;

    @Transactional
    public void saveImageForProduct(Long productId, String imagePath, InputStream content) {
        upload(imagePath, content);
        Optional<Product> productById = productRepository.findById(productId);

        productById.ifPresent(product -> imageRepository.save(ProductImage.builder()
                .product(product)
                .imageUrl(imagePath)
                .build()));
    }

    public Optional<byte[]> getImageForProduct(Long imageId) {
        ProductImage productImage = imageRepository.findById(imageId).orElseThrow();
        return get(productImage.getImageUrl());
    }

    @SneakyThrows
    private Optional<byte[]> get(String imagePath) {
        Path fullImagePath = Path.of(bucket, imagePath);

        return Files.exists(fullImagePath)
                ? Optional.of(Files.readAllBytes(fullImagePath))
                : Optional.empty();
    }

    @SneakyThrows
    private void upload(String imagePath, InputStream content) {
        Path fullImagePath = Path.of(bucket, imagePath);

        try (content) {
            Files.createDirectories(fullImagePath.getParent());
            Files.write(fullImagePath, content.readAllBytes(), CREATE, TRUNCATE_EXISTING);
        }
    }
}
