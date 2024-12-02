package org.market.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice(basePackages = "org.market.controller")
public class RestControllerExceptionHandler {

    @ExceptionHandler(Exception.class)
    public String productNotFound(Exception exception) {
        log.error("Failed to return response", exception);
        return "product/product-not-found";
    }
}
