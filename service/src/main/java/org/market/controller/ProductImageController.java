package org.market.controller;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.market.service.ProductImageService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@Controller
@RequestMapping("/{productId}/images")
@RequiredArgsConstructor
public class ProductImageController {

    private final ProductImageService productImageService;

    @GetMapping("/{imageId}")
    @ResponseBody
    public ResponseEntity<byte[]> getImagesByProductId(@PathVariable Long imageId) {
        Optional<byte[]> images = productImageService.getImageForProduct(imageId);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_OCTET_STREAM_VALUE)
                .body(images.get());
    }

    @SneakyThrows
    @PostMapping
    public String uploadImage(@RequestParam("file") MultipartFile file, @PathVariable Long productId) {
        productImageService.saveImageForProduct(productId, file.getOriginalFilename(), file.getInputStream());
        return "redirect:/products/" + productId;
    }
}
