package org.market.controller;

import lombok.RequiredArgsConstructor;
import org.market.controller.dto.CategoryReadDto;
import org.market.controller.dto.ProductCreateEditDto;
import org.market.controller.dto.ProductReadDto;
import org.market.entity.ProductImage;
import org.market.exception.ProductNotFoundException;
import org.market.service.CategoryService;
import org.market.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final CategoryService categoryService;

    @GetMapping("/create")
    public String getCreateProductPage(Model model) {
        List<CategoryReadDto> allCategories = categoryService.getAllCategories();
        model.addAttribute("categories", allCategories);
        return "product/product-create-page";
    }

    @GetMapping
    public String getAllProducts(Model model) {
        List<ProductReadDto> allProducts = productService.findAll();
        model.addAttribute("products", allProducts);
        return "product/products";
    }

    @PostMapping
    public String createProduct(@ModelAttribute("product") ProductCreateEditDto productDto) {
        ProductReadDto savedProduct = productService.create(productDto);
        return "redirect:/products/%d".formatted(savedProduct.id());
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable Long id, Model model) {
        Optional<ProductReadDto> productById = productService.findById(id);
        List<ProductImage> imagesForProduct = productService.getImageForProduct(id);

        model.addAttribute("product", productById.orElseThrow(ProductNotFoundException::new));
        model.addAttribute("images", imagesForProduct);

        return "product/product-info";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        productService.delete(id);
        return "redirect:/products";
    }

    @PostMapping("/{id}/update")
    public String update(@PathVariable("id") Long id,
                         @ModelAttribute("product") @Validated ProductCreateEditDto product,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes
    ) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            return "redirect:/products{id}/update";
        }
        productService.update(id, product);
        return "redirect:/products/{id}";
    }

    @GetMapping("/{id}/update")
    public String getUpdatePage(@PathVariable("id") Long id, Model model) {
        Optional<ProductReadDto> productById = productService.findById(id);
        List<ProductImage> imagesForProduct = productService.getImageForProduct(id);
        List<CategoryReadDto> allCategories = categoryService.getAllCategories();

        model.addAttribute("categories", allCategories);
        model.addAttribute("product", productById.orElseThrow(ProductNotFoundException::new));
        model.addAttribute("images", imagesForProduct);
        return "product/product-update-page";
    }
}
