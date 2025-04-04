package dto;

import entity.LevelProgress;

import java.time.LocalDate;
import java.util.List;

public record LevelProgressDTO(
    String swimmerName,
    String levelName,
    List<String> requirementStatus,
    boolean passedLevel,
    LocalDate datePassed
) {
    public LevelProgressDTO(LevelProgress levelProgress, String swimmerName, String levelName) {
        this(
                swimmerName,
                levelName,
                levelProgress.getRequirementStatus(),
                levelProgress.isPassedLevel(),
                levelProgress.getDatePassed()
        );
    }
}
