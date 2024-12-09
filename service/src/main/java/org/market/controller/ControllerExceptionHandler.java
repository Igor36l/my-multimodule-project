package org.market.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.market.entity.User;
import org.market.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;

@Slf4j
@ControllerAdvice(basePackages = "org.market.controller")
@RequiredArgsConstructor
public class ControllerExceptionHandler {

    private final UserService userService;

    @ExceptionHandler(Exception.class)
    public String productNotFound(Exception exception) {
        log.error("Failed to return response", exception);
        return "product/product-not-found";
    }

    @ModelAttribute("currentUser")
    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.getPrincipal() != null) {
            String username = authentication.getName();
            return userService.loadUserByUsername(username);
        }
        return null;
    }
}
