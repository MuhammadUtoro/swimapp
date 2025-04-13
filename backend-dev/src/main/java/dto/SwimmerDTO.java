package dto;

import entity.Swimmer;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record SwimmerDTO(
        String swimmerId,
        @NotBlank(message = "Field cannot be empty or blank!")
        String lastName,
        @NotBlank(message = "Field cannot be empty or blank!")
        String firstName,
        @NotBlank(message = "Field cannot be empty or blank!")
        String levelName,
        @NotBlank(message = "Field cannot be empty or blank!")
        String courseName,
        @Email
        String parentEmail,
        List<String> comments

) {
    public SwimmerDTO(Swimmer swimmer, String levelName, String courseName, String parentEmail, List<String> comments) {
        this(
                swimmer.id != null ? swimmer.id.toHexString() : null,
                swimmer.getLastName(),
                swimmer.getFirstName(),
                levelName,
                courseName,
                parentEmail,
                comments
        );
    }
}
