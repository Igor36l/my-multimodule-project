package org.market.service;

import lombok.RequiredArgsConstructor;
import org.market.controller.dto.BucketCreateEditDto;
import org.market.controller.dto.BucketReadDto;
import org.market.entity.Bucket;
import org.market.entity.Product;
import org.market.entity.User;
import org.market.mapper.BucketReadMapper;
import org.market.repository.BucketRepository;
import org.market.repository.ProductRepository;
import org.market.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BucketService {

    private final BucketRepository bucketRepository;
    private final BucketReadMapper bucketReadMapper;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public Optional<BucketReadDto> findBucketByUserId(Long userId) {
        Optional<Bucket> bucketOptional = bucketRepository.findByUserId(userId);
        if (bucketOptional.isEmpty()){
            Bucket newBucket = new Bucket();
            User user = userRepository.findById(userId).orElseThrow();
            newBucket.setUser(user);
            bucketRepository.save(newBucket);
            return Optional.of(bucketReadMapper.map(newBucket));
        }
        return bucketOptional.map(bucketReadMapper::map);
    }

    public Optional<BucketReadDto> addProduct(Long userId, BucketCreateEditDto bucketDto) {
        Optional<Bucket> bucketOptional = bucketRepository.findByUserId(userId);
        Bucket bucket = bucketOptional.orElseGet(() -> {
            Bucket newBucket = new Bucket();
            User user = userRepository.findById(userId).orElseThrow();
            newBucket.setUser(user);
            return newBucket;
        });
        Product product = productRepository.findById(bucketDto.productId()).orElseThrow();
        Optional<Product> first = bucket.getProduct().stream().filter(product1 -> Objects.equals(product1.getId(), product.getId())).findFirst();
        if (first.isEmpty()){
            bucket.getProduct().add(product);
            bucketRepository.save(bucket);
        }
        return bucketOptional.map(bucketReadMapper::map);
    }

    public void deleteProduct(Long userId, Long productId) {
        Bucket bucket = bucketRepository.findByUserId(userId).orElseThrow();
        List<Product> list = bucket.getProduct().stream().filter(product -> Objects.equals(product.getId(), productId)).toList();
        bucket.getProduct().removeAll(list);
        bucketRepository.save(bucket);
    }
}
