package dto;

import java.time.DayOfWeek;
import java.time.LocalTime;
// import java.util.List;

// import org.bson.types.ObjectId;

import entity.Course;
import jakarta.validation.constraints.NotBlank;

public record CourseDTO(
    String courseId,
    @NotBlank(message = "Field cannot be empty or blank!")
    String courseName,
    @NotBlank(message = "Field cannot be empty or blank!")
    DayOfWeek courseDay,
    @NotBlank(message = "Field cannot be empty or blank!")
    String courseTime
    // List<ObjectId> trainerIds
) {

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
