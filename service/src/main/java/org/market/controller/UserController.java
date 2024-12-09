package org.market.controller;

import lombok.RequiredArgsConstructor;
import org.market.controller.dto.UserCreateEditDto;
import org.market.controller.dto.UserReadDto;
import org.market.entity.User;
import org.market.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @GetMapping
    public String findAll(Model model) {
        model.addAttribute("users", userService.findAll());
        return "users/users-page";
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable Long id, Model model) {
        return userService.findById(id)
                .map(user -> {
                    model.addAttribute("user", user);
                    model.addAttribute("genders", User.Gender.values());
                    return "users/user-info-page";
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping
//    @ResponseStatus(HttpStatus.CREATED)
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

    //    @PutMapping("/{id}")
    @PutMapping("/{id}/update")
    public String update(@PathVariable Long id, @ModelAttribute("currentUser") UserCreateEditDto userDto) {
        userService.update(id, userDto);
        return "redirect:/products";
    }

    //    @DeleteMapping("/{id}")
    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        userService.delete(id);
        return "redirect:/products";
    }
}
