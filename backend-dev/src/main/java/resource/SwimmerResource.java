package resource;

import dto.SwimmerDTO;
import entity.Course;
import entity.Level;
import entity.Swimmer;
import entity.User;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import service.SwimmerService;

import java.util.List;

import org.bson.types.ObjectId;

@Path("swimmers")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class SwimmerResource {

    @Inject
    SwimmerService swimmerService;

    @GET
    // @RolesAllowed({"ADMIN", "TRAINER"})
    public Response getAllSwimmers() {

        List<SwimmerDTO> swimmerDTOS = swimmerService.getAllSwimmers();

        return Response.status(Response.Status.OK).entity(swimmerDTOS).build();
    }

    @POST
    // @RolesAllowed("ADMIN")
    public Response addSwimmer(SwimmerDTO swimmerDTO) {
        SwimmerDTO swimmerDTOs = swimmerService.createSwimmerDTO(swimmerDTO);

        return Response.status(Response.Status.CREATED).entity(swimmerDTOs).build();
    }

   @GET
   @Path("/{swimmerId}")
//    @RolesAllowed({"ADMIN", "TRAINER"})
   public Response getSwimmerById(@PathParam("swimmerId") String swimmerId) {

        SwimmerDTO swimmerDTO = swimmerService.getSwimmerById(swimmerId);

        if (swimmerDTO == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.status(Response.Status.OK).entity(swimmerDTO).build();
   }

   @PATCH
   @Path("/{swimmerId}")
   public Response updateSwimmer(@PathParam("swimmerId") String swimmerId, SwimmerDTO swimmerDTO) {

    Swimmer updatedSwimmer = swimmerService.updateSwimmer(swimmerId, swimmerDTO);

    // Fetch all the arguments for SwimmerDTO constructor
    Level level = Level.findById(updatedSwimmer.getLevelId());
    Course course = Course.findById(updatedSwimmer.getCourseId());
    User parent = User.findById(updatedSwimmer.getParentId());

    SwimmerDTO updatedSwimmerDTO = new SwimmerDTO(updatedSwimmer, level, course, parent);

    return Response.status(Response.Status.OK).entity(updatedSwimmerDTO).build();
   }
}
