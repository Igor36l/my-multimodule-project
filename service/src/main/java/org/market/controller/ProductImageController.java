package org.market.controller;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.market.service.ProductImageService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/{productId}/images")
@RequiredArgsConstructor
public class ProductImageController {

    private final ProductImageService productImageService;

    @SneakyThrows
    @PostMapping
    public String uploadImage(@RequestParam("file") MultipartFile file, @PathVariable Long productId) {
        productImageService.saveImageForProduct(productId, file.getOriginalFilename(), file.getInputStream());
        return "redirect:/products/" + productId;
    }
}
