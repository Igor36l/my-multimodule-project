package org.market.service;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.market.entity.Product;
import org.market.entity.ProductImage;
import org.market.repository.ImageRepository;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

import static java.nio.file.StandardOpenOption.CREATE;
import static java.nio.file.StandardOpenOption.TRUNCATE_EXISTING;

@Service
@RequiredArgsConstructor
public class ProductImageService {

    //    @Value("${app.image.bucket:/home/study/IdeaProjects/my-multimodule-project/images}")
    private final String bucket = "/home/study/IdeaProjects/my-multimodule-project/images";

    private final ProductService productService;
    private final ImageRepository imageRepository;


    public void saveImageForProduct(Long productId, String imagePath, InputStream content) {
        upload(imagePath, content);
        Optional<Product> productById = productService.getProductById(productId);

        productById.ifPresent(product -> imageRepository.save(ProductImage.builder()
                .product(product)
                .imageUrl(imagePath)
                .build()));
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
