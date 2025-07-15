package service;

import dto.SwimmerDTO;
import entity.*;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.NotFoundException;

import java.util.List;
import java.util.stream.Collectors;

import org.bson.types.ObjectId;

@ApplicationScoped
public class SwimmerService {

    // For get endpoint
    public List<SwimmerDTO> getAllSwimmers() {
        return Swimmer.<Swimmer>listAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    // Get swimmer by ID
    public SwimmerDTO getSwimmerById(String swimmerId) {

        Swimmer swimmer = Swimmer.findById(new ObjectId(swimmerId));

        if (swimmer == null) {
            return null;
        }

        return toDTO(swimmer);
    }

    // Create swimmer
    public SwimmerDTO createSwimmerDTO(SwimmerDTO swimmerDTO) {
        
        // Fetch level, course, and email according to their name
        Level level = Level.find("levelName", swimmerDTO.levelName()).firstResult();
        User parentEmail = User.find("userEmail", swimmerDTO.parentEmail()).firstResult();
        Course course = Course.findById(new ObjectId(swimmerDTO.courseId())); 

        // Check if any of the data exist
        if (level == null || course == null || parentEmail == null) {
            throw new NotFoundException("Level, Course, or Parent Email does not exist!");
        }

        // If found, then we create a new swimmer entity
        Swimmer swimmer = new Swimmer();

        swimmer.setLastName(swimmerDTO.firstName());
        swimmer.setFirstName(swimmerDTO.lastName());
        swimmer.setLevelId(level.id);
        swimmer.setCourseId(course.id);
        swimmer.setParentId(parentEmail.id);

        // Persist the new swimmer
        swimmer.persist();

        // Return DTO
        return toDTO(swimmer);
    }

    // For patch endpoint
    public Swimmer updatSwimmer(String swimmerId, SwimmerDTO updatedSwimmerDTO) {

        // Find the swimmer
        Swimmer swimmer = Swimmer.findById(new ObjectId(swimmerId));

        if(swimmer == null) {
            throw new NotFoundException("Swimmer not found");
        }

        // Update the field
        swimmer.updateSwimmerFromDTO(updatedSwimmerDTO);
        
        // Persist to database
        swimmer.update();

        return swimmer;
    }

    // Helper method to convert entity to DTO
    private SwimmerDTO toDTO(Swimmer swimmer) {

        // Fetch Level
        Level level = null;
        if (swimmer.getLevelId() != null) {
            level = Level.findById(swimmer.getLevelId());
        }

        // Fetch Course
        Course course = null;
        if (swimmer.getCourseId() != null) {
            course = Course.findById(swimmer.getCourseId());
        }
        
        // Fetch Parent Email
        User parent = null;
        if (swimmer.getParentId() != null) {
            parent = User.findById(swimmer.getParentId());
        }

        // Fetch comments
        // final List<String> comments = swimmer.getComments().stream()
        //         .map(commentId -> {
        //             Comment comment = Comment.findById(commentId);
        //             return (comment != null) ? comment.getText() : null;
        //         })
        //         .filter(Objects::nonNull)
        //         .toList();

        return new SwimmerDTO(swimmer, level, course, parent);
    }
}
