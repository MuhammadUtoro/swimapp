package dto;

import java.time.DayOfWeek;

import entity.Course;
import entity.Level;
import entity.Swimmer;
import entity.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

// import java.util.List;

public record SwimmerDTO(
        String swimmerId,
        @NotBlank(message = "Field cannot be empty or blank!")
        String lastName,
        @NotBlank(message = "Field cannot be empty or blank!")
        String firstName,
        @NotBlank(message = "Field cannot be empty or blank!")
        String levelName,
        @NotBlank(message = "Field cannot be empty or blank!")
        String courseId,
        String courseName,
        DayOfWeek courseDay, 
        String courseTime,
        @Email
        String parentEmail
        // List<String> comments

) {
    public SwimmerDTO(Swimmer swimmer, Level level, Course course, User parent) {
        this(
                swimmer.id != null ? swimmer.id.toHexString() : null,
                swimmer.getLastName(),
                swimmer.getFirstName(),
                level.getLevelName(),
                course.id != null ? course.id.toHexString() : null,
                course.getCourseName(),
                course.getCourseDay(),
                course.getCourseTime(),
                parent.getUserEmail()
                // comments
        );
    }
}
