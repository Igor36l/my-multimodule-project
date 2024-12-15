package org.market.controller;

import lombok.RequiredArgsConstructor;
import org.market.controller.dto.UserCreateEditDto;
import org.market.entity.User;
import org.market.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
@RequiredArgsConstructor
public class LoginController {

    private final UserService userService;

    @GetMapping("/login")
    public String loginPage(Model model) {
        return "/users/login-page";
    }

    @GetMapping("/registration")
    public String registration(Model model, @ModelAttribute("user") UserCreateEditDto userCreateEditDto) {
        model.addAttribute("genders", User.Gender.values());
        return "/users/registration-page";
    }
}
