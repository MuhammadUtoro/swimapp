package resource;

import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("levels")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class LevelResource {

    @Inject
    LevelService LevelService;

    @GET
    @RolesAllowed("ADMIN")
    public Response getAllLevels() {

        List<LevelDTO> levelDTOs = LevelService.getAllLevels();

        return Response.status(Response.Status.OK).entity(levelDTOs).build();
    }
}
