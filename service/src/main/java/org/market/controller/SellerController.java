package org.market.controller;

import lombok.RequiredArgsConstructor;
import org.market.controller.dto.SellerCreateEditDto;
import org.market.controller.dto.SellerReadDto;
import org.market.service.SellerService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("users/{userId}/sellers")
public class SellerController {

    private final SellerService sellerService;

    @PostMapping
    public String createSeller(@PathVariable("userId") Long id, @ModelAttribute("seller") SellerCreateEditDto dto){
        Optional<SellerReadDto> sellerReadDto = sellerService.create(id, dto);
        return "redirect:/users/{userId}";
    }
}
