package entity;

import io.quarkus.mongodb.panache.PanacheMongoEntity;
import io.quarkus.mongodb.panache.common.MongoEntity;
import org.bson.types.ObjectId;

import dto.CourseDTO;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;
import java.util.function.Consumer;

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

    public Course(CourseDTO courseDTO) {
        this.courseName = courseDTO.courseName();
        this.courseDay = courseDTO.courseDay();
        this.courseTime = courseDTO.courseTime();
        this.trainerIds = courseDTO.trainerIds();
    }

    public void updateCourseFromDTO(CourseDTO updatedCourseDTO) {
        updateFieldIfNotNull(updatedCourseDTO.courseName(), this::setCourseName);
        updateFieldIfNotNull(updatedCourseDTO.courseDay(), this::setCourseDay);
        updateFieldIfNotNull(updatedCourseDTO.courseTime(), this::setCourseTime);
        updateFieldIfNotNull(updatedCourseDTO.trainerIds(), this::setTrainerIds);
    }

    // Helper method to update field if the value is not null
    private <T> void updateFieldIfNotNull(T value, Consumer<T> setter) {
        if (value != null) {
            setter.accept(value);
        }
    }

}
