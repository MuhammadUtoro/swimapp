package dto;

import java.util.List;

import org.bson.types.ObjectId;

import jakarta.validation.constraints.NotBlank;

public record CourseDTO(
    String courseId,
    @NotBlank(message = "Field cannot be empty or blank!")
    String courseName,
    @NotBlank(message = "Field cannot be empty or blank!")
    String courseDay,
    @NotBlank(message = "Field cannot be empty or blank!")
    String courseTime,
    List<ObjectId> trainerIds
) {

}
