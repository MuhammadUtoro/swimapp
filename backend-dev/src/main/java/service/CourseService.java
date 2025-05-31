package service;

import java.util.List;
import java.util.stream.Collectors;

import dto.CourseDTO;
import entity.Course;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CourseService {

    // For get endpoint
    public List<CourseDTO> getAllCourses() {
        return Course.<Course>listAll()
        .stream()
        .map(this::toDTO)
        .collect(Collectors.toList());
    }
    
    // For post endpoint
    public CourseDTO addCourse(CourseDTO courseDTO) {

        // Create a new course entity
        Course course = new Course(courseDTO);

        // Persist into database
        course.persist();

        // Return a DTO
        return new CourseDTO(course);
    } 
    
    // Helper method
    private CourseDTO toDTO(Course course) {
        return new CourseDTO(course);
    }
}
