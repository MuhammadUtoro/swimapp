package dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record LoginDTO(
        @Email
        String userEmail,

        @NotBlank(message = "Field cannot be empty or blank!")
        String password
) {
}
