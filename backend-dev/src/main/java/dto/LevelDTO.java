package dto;

import entity.Level;

import java.util.List;

public record LevelDTO(

        String levelName,
        List<String> requirements
) {

    public LevelDTO(Level level) {
        this(
                level.getLevelName(),
                level.getRequirements()
        );
    }
}
