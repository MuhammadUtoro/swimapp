package entity;

import io.quarkus.mongodb.panache.PanacheMongoEntity;
import io.quarkus.mongodb.panache.common.MongoEntity;

import java.util.List;

import dto.LevelDTO;

@MongoEntity
public class Level extends PanacheMongoEntity {

    private String levelName;
    private List<String> requirements;

    public List<String> getRequirements() {
        return requirements;
    }

    public void setRequirements(List<String> requirements) {
        this.requirements = requirements;
    }

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    public Level(LevelDTO levelDTO) {
        this.levelName = levelDTO.levelName();
        this.requirements = levelDTO.requirements();
    }

    public Level() {}
}
