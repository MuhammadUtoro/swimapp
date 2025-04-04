package dto;

import entity.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record RegistrationDTO(
       String userId,
       @NotBlank(message = "Field cannot be empty or blank!")
       String lastName,
       @NotBlank(message = "Field cannot be empty or blank!")
       String firstName,
       @Email
       String userEmail,
       @NotBlank(message = "Field cannot be empty or blank!")
       String password
) {

    public RegistrationDTO(User user) {
        this(
                // check if userId is null
                user.id != null ? user.id.toHexString() : null,
                user.getLastName(),
                user.getFirstName(),
                user.getUserEmail(),
                user.getPassword()
        );
    }
}
