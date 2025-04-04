package dto;

import entity.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserDTO(
        String userId,
        @NotBlank(message = "Field cannot be empty or blank!")
        String lastName,
        @NotBlank(message = "Field cannot be empty or blank")
        String firstName,
        @Email
        String userEmail,
        User.Role role
) {

    public UserDTO(User user) {
        this(
                user.id != null ? user.id.toHexString() : null,
                user.getLastName(),
                user.getFirstName(),
                user.getUserEmail(),
                user.getRole()
        );
    }

}
