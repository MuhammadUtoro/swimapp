package resource;

import dto.UserDTO;
import entity.User;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import service.UserService;

import java.util.List;

@Path("users")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {

    @Inject
    UserService userService;

    @GET
    // @RolesAllowed("ADMIN")
    public Response getAllUsers() {
        List<UserDTO> userDTOs = userService.getAllUsers();

        return Response.status(Response.Status.OK).entity(userDTOs).build();
    }

    @GET
    @Path("/{userId}")
    @RolesAllowed("ADMIN")
    public Response getUserById(@PathParam("userId") String userId) {
        UserDTO userDTO = userService.getUserById(userId);

        if (userDTO == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("User not found!").build();
        }

        return Response.status(Response.Status.OK).entity(userDTO).build();
    }

    @PATCH
    @Path("/{userId}")
    @RolesAllowed("ADMIN")
    public Response updateUser(@PathParam("userId") String userId, UserDTO userDTO) {

        User updatedUser = userService.updateUser(userId, userDTO);

        return Response.status(Response.Status.OK).entity(new UserDTO(updatedUser)).build();
    }

    @DELETE
    @Path("/{userId}")
    @RolesAllowed("ADMIN")
    public Response deleteUser(@PathParam("userId") String userId) {

        userService.deleteUser(userId);
        return Response.ok("User successfully deleted!").build();

    }
}
