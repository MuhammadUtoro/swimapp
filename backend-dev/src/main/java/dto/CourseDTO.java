package dto;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import entity.Course;
import jakarta.validation.constraints.NotBlank;

public record CourseDTO(
    String courseId,
    @NotBlank(message = "Field cannot be empty or blank!")
    String courseName,
    @NotBlank(message = "Field cannot be empty or blank!")
    DayOfWeek courseDay,
    String courseTime
    // List<ObjectId> trainerIds
) {

    public LocalTime toLocalTime() {
        return LocalTime.parse(courseTime, DateTimeFormatter.ofPattern("HH:mm"));
    }

    public CourseDTO(Course course) {
        this(
            course.id != null ? course.id.toHexString() : null,
            course.getCourseName(),
            course.getCourseDay(),
            course.getCourseTime().toString()
            // course.getTrainerIds()
        );
    }
}
