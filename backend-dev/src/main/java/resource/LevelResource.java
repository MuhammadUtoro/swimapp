package resource;

import java.util.List;

import dto.LevelDTO;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import service.LevelService;

@Path("levels")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class LevelResource {

    @Inject
    LevelService levelService;

    @GET
    // @RolesAllowed("ADMIN")
    public Response getAllLevels() {

        List<LevelDTO> levelDTOs = levelService.getAllLevels();

        return Response.status(Response.Status.OK).entity(levelDTOs).build();
    }

    @POST
    // @RolesAllowed("ADMIN")
    public Response addLevel(LevelDTO levelDTO) {

        LevelDTO levelDTOs = levelService.addLevel(levelDTO);

        return Response.status(Response.Status.CREATED).entity(levelDTO).build();
    }
}
