package org.market.service;

import lombok.RequiredArgsConstructor;
import org.market.mapper.SellerCreateEditMapper;
import org.market.mapper.SellerReadMapper;
import org.market.repository.SellerRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SellerService {

    private final SellerRepository sellerRepository;
    private final SellerReadMapper sellerReadMapper;
    private final SellerCreateEditMapper sellerCreateEditMapper;


}
