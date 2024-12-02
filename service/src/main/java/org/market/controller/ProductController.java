package org.market.controller;

import lombok.RequiredArgsConstructor;
import org.market.controller.dto.ProductCreateEditDto;
import org.market.entity.Product;
import org.market.exception.ProductNotFoundException;
import org.market.mapper.ProductMapper;
import org.market.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public String getAllProducts(Model model) {
        List<Product> allProducts = productService.getAllProducts();
        model.addAttribute("products", allProducts);
        return "product/products";
    }

    @PostMapping
    public String createProduct(ProductCreateEditDto productDto) {
        Product product = ProductMapper.toProduct(productDto);
        Product savedProduct = productService.createProduct(product);
        return "redirect:/products/%d".formatted(savedProduct.getId());
    }

    @GetMapping("/{id}")
    public String findProductById(@PathVariable Long id, Model model) {
        Optional<Product> productById = productService.getProductById(id);
        model.addAttribute("product", productById.orElseThrow(ProductNotFoundException::new));
        return "product/product-info";
    }


    @PostMapping("/{id}/delete")
    public String deleteProduct(@PathVariable Long id) {
        productService.deleteProductById(id);
        return "redirect:/products";
    }

    @PostMapping("/{id}/update")
    public String update(@PathVariable("id") Long id,
                         @ModelAttribute ProductCreateEditDto product) {
        productService.update(id, product);
        return "redirect:/products/%d".formatted(id);
    }
}
