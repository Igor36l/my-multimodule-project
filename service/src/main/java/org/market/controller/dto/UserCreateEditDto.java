package org.market.controller.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

public record UserCreateEditDto(

        @Size(min = 3, message = "Username length should be more 3 chars")
        String username,

        @Email
        String email,

        String password,

        String gender) {
}
