package dto;

import java.time.DayOfWeek;
import java.time.LocalTime;

import entity.Course;
import entity.Swimmer;
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
        String courseName,
        DayOfWeek courseDay,
        String courseTime,
        @Email
        String parentEmail
        // List<String> comments

) {
    public SwimmerDTO(Swimmer swimmer, String levelName, Course course, String parentEmail) {
        this(
                swimmer.id != null ? swimmer.id.toHexString() : null,
                swimmer.getLastName(),
                swimmer.getFirstName(),
                levelName,
                course != null ? course.getCourseName() : null,
                course != null ? course.getCourseDay() : null,
                course != null ? course.getCourseTime().toString() : null,
                parentEmail
                // comments
        );
    }
}
