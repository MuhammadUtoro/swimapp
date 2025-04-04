package service;

import dto.LoginDTO;
import dto.RegistrationDTO;
import dto.UserDTO;
import entity.User;
import io.quarkus.elytron.security.common.BcryptUtil;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.NotFoundException;
import org.bson.types.ObjectId;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@ApplicationScoped
public class UserService {

    // For GET endpoint
    public List<UserDTO> getAllUsers() {
        return User.<User>listAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    // Get user by ID
    public UserDTO getUserById(String userId) {
        User user = User.findById(new ObjectId(userId));

        if (user == null) {
            return  null;
        }
        return new UserDTO(user);
    }

    // For PATCH endpoint
    public User updateUser(String userId, UserDTO updatedUserDTO) {

        // Find the user
        User user = User.findById(new ObjectId(userId));

        if (user == null) {
            throw new NotFoundException("User not found!");
        }

        // Update the fields
        user.updateUserFromDTO(updatedUserDTO);

        // Persist to database
        user.update();

        // return user entity
        return user;
    }

    // For delete endpoint
    public void deleteUser(String userId) {

        User user = User.findById(new ObjectId(userId));

        if (user == null) {
            throw new NotFoundException("User not found!");
        }

        user.delete();
    }

    // For registration purpose
    public UserDTO registerUser(RegistrationDTO registrationDTO) {

        // Hash password before storing
        String hashedPassword = BcryptUtil.bcryptHash(registrationDTO.password());

        // Create User Entity from RegistrationDTO
        User user = new User(registrationDTO, hashedPassword);
        user.setRole(User.Role.ADMIN);

        // Persist to Database
        user.persist();

        // Return a DTO (to make it consistent)
        return new UserDTO(user);
    }

    // For login check
    public boolean userLogin(LoginDTO loginDTO, User user) {
        if (!loginDTO.userEmail().equals(user.getUserEmail())) {
            return false;
        }
        return BcryptUtil.matches(loginDTO.password(), user.getPassword());
    }

   // For login purpose (returning Entity)
   public User getUserByEmail(String userEmail) {
        return User.find("userEmail", userEmail).firstResult();
   }

   // Return DTO for client-side purpose
    public RegistrationDTO getUserDTObyEmail(String userEmail) {
        Optional<User> userOptional = User.find("userEmail", userEmail).firstResultOptional();
        return userOptional.map(RegistrationDTO::new).orElse(null);
    }

    // Helper method to convert Entity to DTO
    private UserDTO toDTO(User user) {
        return new UserDTO(user);
    }
}
