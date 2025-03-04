package io.github.sunjoo_kim.board.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class SignUpRequest {
    @NotBlank
    @Size(min = 3, max = 20)
    private String username;

    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    @NotBlank
    @Size(min = 6, max = 40)
    private String password;

    public CharSequence getPassword() {
        return null;
    }

    public String getUsername() {
        return "";
    }

    public String getEmail() {
        return "";
    }

    // Getters and Setters
}
