package org.market.controller.dto;

public record UserReadDto(Long id, String username, String email, String password, String gender) {
}
