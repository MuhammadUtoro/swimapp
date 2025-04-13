package entity;

import io.quarkus.mongodb.panache.PanacheMongoEntity;
import io.quarkus.mongodb.panache.common.MongoEntity;
import org.bson.types.ObjectId;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;

@MongoEntity
public class Course extends PanacheMongoEntity {

    private String courseName;
    private DayOfWeek courseDay;
    private LocalTime courseTime;
    private List<ObjectId> trainerIds;

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public DayOfWeek getCourseDay() {
        return courseDay;
    }

    public void setCourseDay(DayOfWeek courseDay) {
        this.courseDay = courseDay;
    }

    public LocalTime getCourseTime() {
        return courseTime;
    }

    public void setCourseTime(LocalTime courseTime) {
        this.courseTime = courseTime;
    }

    public List<ObjectId> getTrainerIds() {
        return trainerIds;
    }

    public void setTrainerIds(List<ObjectId> trainerIds) {
        this.trainerIds = trainerIds;
    }

}
