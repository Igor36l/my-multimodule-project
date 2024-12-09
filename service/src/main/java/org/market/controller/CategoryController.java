package org.market.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.market.controller.dto.CategoryCreateEditDto;
import org.market.controller.dto.CategoryReadDto;
import org.market.service.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    public String findAll(Model model){
        List<CategoryReadDto> allCategories = categoryService.getAllCategories();
        model.addAttribute("categories", allCategories);
        return "category/categories";
    }

    @PostMapping
    public String createCategory(@ModelAttribute("category") CategoryCreateEditDto dto,
                                 RedirectAttributes redirectAttributes,
                                 HttpServletRequest request){
        CategoryReadDto category = categoryService.createCategory(dto);
        redirectAttributes.addFlashAttribute("category", List.of(category));

        String referer = request.getHeader("referer");

        return "redirect:" + (referer != null ? referer : "/products");
    }
}
