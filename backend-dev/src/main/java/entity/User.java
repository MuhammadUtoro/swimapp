package entity;

import dto.RegistrationDTO;
import dto.UserDTO;
import io.quarkus.mongodb.panache.PanacheMongoEntity;
import io.quarkus.mongodb.panache.common.MongoEntity;

import java.util.function.Consumer;

@MongoEntity
public class User extends PanacheMongoEntity {

    public User() {}

    private String lastName;
    private String firstName;
    private String password;
    private String userEmail;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public enum Role {
        ADMIN,
        TRAINER,
        PARENT,
    }

    private Role role;

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public User(RegistrationDTO registrationDTO, String hashedPassword) {
        this.firstName = registrationDTO.firstName();
        this.lastName = registrationDTO.lastName();
        this.userEmail = registrationDTO.userEmail();
        this.password = hashedPassword;
    }

    public User(UserDTO userDTO, String hashedPassword) {
       this.firstName = userDTO.firstName();
       this.lastName = userDTO.lastName();
       this.userEmail = userDTO.userEmail();
    }

    public void updateUserFromDTO(UserDTO updatedUserDTO) {

        // Using the helper method
        updateFieldIfNotNull(updatedUserDTO.lastName(), this::setLastName);
        updateFieldIfNotNull(updatedUserDTO.firstName(), this::setFirstName);
        updateFieldIfNotNull(updatedUserDTO.userEmail(), this::setUserEmail);
        updateFieldIfNotNull(updatedUserDTO.role(), this::setRole);
    }

    // Helper method to update a field if the value is not null
    private <T> void updateFieldIfNotNull(T value, Consumer<T> setter) {
        if (value != null) {
            setter.accept(value);
        }
    }
}
