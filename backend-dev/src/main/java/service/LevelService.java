package service;

import java.util.List;
import java.util.stream.Collectors;

import dto.LevelDTO;
import entity.Level;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class LevelService {

    // For get endpoint
    public List<LevelDTO> getAllLevels() {
        return Level.<Level>listAll()
        .stream()
        .map(this::toDTO)
        .collect(Collectors.toList());
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

    // Helper method to convert from entity to DTO
    private LevelDTO toDTO(Level level) {
        return new LevelDTO(level);
    }
}