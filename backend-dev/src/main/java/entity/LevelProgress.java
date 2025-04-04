package entity;

import io.quarkus.mongodb.panache.PanacheMongoEntity;
import io.quarkus.mongodb.panache.common.MongoEntity;
import org.bson.types.ObjectId;

import java.time.LocalDate;
import java.util.List;

@MongoEntity
public class LevelProgress extends PanacheMongoEntity {

    private ObjectId swimmer;

    public ObjectId getSwimmer() {
        return swimmer;
    }

    public void setSwimmer(ObjectId swimmer) {
        this.swimmer = swimmer;
    }

    public ObjectId getLevel() {
        return level;
    }

    public void setLevel(ObjectId level) {
        this.level = level;
    }

    public List<String> getRequirementStatus() {
        return requirementStatus;
    }

    public void setRequirementStatus(List<String> requirementStatus) {
        this.requirementStatus = requirementStatus;
    }

    public boolean isPassedLevel() {
        return passedLevel;
    }

    public void setPassedLevel(boolean passedLevel) {
        this.passedLevel = passedLevel;
    }

    public LocalDate getDatePassed() {
        return datePassed;
    }

    public void setDatePassed(LocalDate datePassed) {
        this.datePassed = datePassed;
    }

    private ObjectId level;
    private List<String> requirementStatus;
    private boolean passedLevel = false; // default value
    private LocalDate datePassed;

}
