package org.market.controller;

import lombok.RequiredArgsConstructor;
import org.market.controller.dto.*;
import org.market.entity.User;
import org.market.service.BucketService;
import org.market.service.SellerService;
import org.market.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final SellerService sellerService;
    private final BucketService bucketService;

    @GetMapping
    public String findAll(Model model) {
        model.addAttribute("users", userService.findAll());
        return "users/users-page";
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable Long id, Model model, @ModelAttribute("seller") SellerCreateEditDto dto) {
        return userService.findById(id)
                .map(user -> {
                    model.addAttribute("user", user);
                    model.addAttribute("genders", User.Gender.values());
                    if (user.isSeller()) {
                        model.addAttribute("seller", sellerService.findByUserId(id)
                                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)));
                    }
                    return "users/user-info-page";
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public String createUser(@ModelAttribute("user") @Validated UserCreateEditDto userDto, BindingResult bindingResult,
                             RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("user", userDto);
            redirectAttributes.addFlashAttribute("errors", bindingResult.getFieldErrors());
            return "redirect:/registration";
        }
        UserReadDto user = userService.createUser(userDto);

        return "redirect:/products";
    }

    @PostMapping("/{id}/update")
    public String update(@PathVariable Long id, @ModelAttribute("user") @Validated UserCreateEditDto userDto,
                         BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            return "redirect:/users/{id}";
        }
        userService.update(id, userDto);
        return "redirect:/products";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        userService.delete(id);
        return "redirect:/products";
    }

    @GetMapping("/{id}/bucket")
    public String getBucket(@PathVariable("id") Long id, Model model){
        BucketReadDto bucketByUserId = bucketService.findBucketByUserId(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        model.addAttribute("bucket", bucketByUserId);
        return "users/user-bucket";
    }

    @PostMapping("/{id}/bucket")
    public String addProductToBucket(@PathVariable("id") Long id,
                                     @RequestHeader("referer") String redirectUrl,
                                     @ModelAttribute("bucket") BucketCreateEditDto bucketDto){
       bucketService.addProduct(id, bucketDto).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return "redirect:" + (redirectUrl != null ? redirectUrl : "/products");
    }

    @PostMapping("/{userId}/bucket/{productId}/delete")
    public String deleteProductFromBucket(@PathVariable("userId") Long userId,
                                     @PathVariable("productId") Long productId,
                                     @RequestHeader("referer") String redirectUrl
    ){
        bucketService.deleteProduct(userId, productId);
        return "redirect:" + (redirectUrl != null ? redirectUrl : "/products");
    }
}
