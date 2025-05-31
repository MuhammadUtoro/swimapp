package service;

import java.util.List;
import java.util.stream.Collectors;

import org.bson.types.ObjectId;

import dto.CourseDTO;
import entity.Course;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class CourseService {

    // For get endpoint
    public List<CourseDTO> getAllCourses() {
        return Course.<Course>listAll()
        .stream()
        .map(this::toDTO)
        .collect(Collectors.toList());
    }

    // Get level by ID
    public CourseDTO getCourseById (String courseId) {
        Course course = Course.findById(new ObjectId(courseId));

        if (course == null) {
            return null;
        }

        return new CourseDTO(course);
    }

    // For patch endpoint
    public Course updateCourse(String courseId, CourseDTO updatedCourseDTO) {

        // Find the course
        Course course = Course.findById(new ObjectId(courseId));

        if (course == null) {
            throw new NotFoundException("Course not found!");
        }
        
        // Update the field
        course.updateCourseFromDTO(updatedCourseDTO);

        // Persist to database
        course.update();

        // Return entity
        return course;
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
    
    // For delete endpoint
    public void deleteCourse(String courseId) {

        Course course = Course.findById(new ObjectId(courseId));

        if(course == null) {
            throw new NotFoundException();
        }

        course.delete();

    }

    // Helper method
    private CourseDTO toDTO(Course course) {
        return new CourseDTO(course);
    }

}
