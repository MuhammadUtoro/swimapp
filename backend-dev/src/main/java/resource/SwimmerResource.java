package resource;

import dto.SwimmerDTO;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import service.SwimmerService;

import java.util.List;

@Path("swimmers")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class SwimmerResource {

    @Inject
    SwimmerService swimmerService;

    @GET
    @RolesAllowed({"ADMIN", "TRAINER"})
    public Response getAllSwimmers() {

        List<SwimmerDTO> swimmerDTOS = swimmerService.getAllSwimmers();

        return Response.status(Response.Status.OK).entity(swimmerDTOS).build();
    }

   @GET
   @Path("/{swimmerId}")
   @RolesAllowed({"ADMIN", "TRAINER"})
   public Response getSwimmerById(@PathParam("swimmerId") String swimmerId) {

        SwimmerDTO swimmerDTO = swimmerService.getSwimmerById(swimmerId);

       return Response.status(Response.Status.NOT_FOUND).entity("User not found!").build();
   }
}
