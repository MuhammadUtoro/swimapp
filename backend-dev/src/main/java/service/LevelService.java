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

    // Helper method to convert from entity to DTO
    private LevelDTO toDTO(Level level) {
        return new LevelDTO(level);
    }
}