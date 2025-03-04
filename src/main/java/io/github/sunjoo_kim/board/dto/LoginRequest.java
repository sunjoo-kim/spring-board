package io.github.sunjoo_kim.board.dto;

import jakarta.validation.constraints.NotBlank;

public class LoginRequest {
    @NotBlank
    private String username;

    @NotBlank
    private String password;

    public Object getUsername() {
        return null;
    }

    public Object getPassword() {
        return null;
    }

    // Getters and Setters
}
