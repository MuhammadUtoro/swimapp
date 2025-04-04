package entity;

import io.quarkus.mongodb.panache.PanacheMongoEntity;
import io.quarkus.mongodb.panache.common.MongoEntity;
import org.bson.types.ObjectId;

import java.util.List;

@MongoEntity
public class Swimmer extends PanacheMongoEntity {

    public Swimmer() {}

    private String lastName;
    private String firstName;
    private ObjectId levelId; // reference to Level
    private ObjectId parentId; // reference to User
    private ObjectId courseId; // reference to entity.Course private List<ObjectId> comments;
    private List<ObjectId> comments;

    public ObjectId getCourseId() {
        return courseId;
    }

    public void setCourseId(ObjectId courseId) {
        this.courseId = courseId;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public ObjectId getLevelId() {
        return levelId;
    }

    public void setLevelId(ObjectId levelId) {
        this.levelId = levelId;
    }

    public ObjectId getParentId() {
        return parentId;
    }

    public void setParentId(ObjectId parentId) {
        this.parentId = parentId;
    }


    public List<ObjectId> getComments() {
        return comments;
    }

    public void setComments(List<ObjectId> comments) {
        this.comments = comments;
    }

}
