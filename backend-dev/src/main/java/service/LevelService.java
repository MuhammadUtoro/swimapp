package service;

import java.util.List;
import java.util.stream.Collectors;

import org.bson.types.ObjectId;

import dto.LevelDTO;
import entity.Level;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class LevelService {

    // For get endpoint
    public List<LevelDTO> getAllLevels() {
        return Level.<Level>listAll()
        .stream()
        .map(this::toDTO)
        .collect(Collectors.toList());
    }

    // For get by Id
    public LevelDTO getLevelById(String levelId) {
        Level level = Level.findById(new ObjectId(levelId));
        
        if (level == null) {
            return null;
        }

        return new LevelDTO(level);
    }

    // For post endpoint
    public LevelDTO addLevel(LevelDTO levelDTO) {

        // Create a new Level entity from DTO
        Level level = new Level(levelDTO);

        // Persist into database
        level.persist();

        // Return DTO (make it consistent)
        return new LevelDTO(level);
    }

    // For patch endpoint
    public Level updateLevel(String levelId, LevelDTO updatedLevelDTO) {

        // Find the level
        Level level = Level.findById(new ObjectId(levelId));

        if (level == null) {
            throw new NotFoundException("Level not found!");
        }

        // Update the field
        level.updateLevelFromDTO(updatedLevelDTO);
        
        // Persist to database
        level.update();

        // return Level entity
        return level;

    }

    // For delete endpoint
    public void deleteLevel(String levelId) {

        Level level = Level.findById(new ObjectId(levelId));

        if(level == null) {
            throw new NotFoundException();
        }

        level.delete();
    }

    // Helper method to convert from entity to DTO
    private LevelDTO toDTO(Level level) {
        return new LevelDTO(level);
    }
}