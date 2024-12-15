package org.market.mapper;

import lombok.RequiredArgsConstructor;
import org.market.controller.dto.BucketReadDto;
import org.market.entity.Bucket;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BucketReadMapper implements Mapper<Bucket, BucketReadDto> {

    private final ProductReadMapper productReadMapper;

    @Override
    public BucketReadDto map(Bucket object) {
        return new BucketReadDto(
                object.getId(),
                object.getProduct().stream().map(productReadMapper::map).toList(),
                object.getUser().getId()
        );
    }
}
