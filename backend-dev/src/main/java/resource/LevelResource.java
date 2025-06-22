package resource;

import java.util.List;

import dto.LevelDTO;
import entity.Level;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
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

    @GET
    @Path("/{levelId}")
    // @RolesAllowed("ADMIN")
    public Response getLevelById(@PathParam("levelId") String levelId) {
        LevelDTO levelDTO = levelService.getLevelById(levelId);

        if (levelDTO == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Level not found!").build();
        }
        
        return Response.status(Response.Status.OK).entity(levelDTO).build();
    }

    @POST
    // @RolesAllowed("ADMIN")
    public Response addLevel(LevelDTO levelDTO) {

        LevelDTO levelDTOs = levelService.addLevel(levelDTO);

        return Response.status(Response.Status.CREATED).entity(levelDTOs).build();
    }

    @PATCH
    @Path("/{levelId}")
    // @RolesAllowed("ADMIN")
    public Response updateLevel(@PathParam("levelId") String levelId, LevelDTO levelDTO) {
        Level updatedLevel = levelService.updateLevel(levelId, levelDTO);

        return Response.status(Response.Status.OK).entity(new LevelDTO(updatedLevel)).build();
    }

    @DELETE
    @Path("/{levelId}")
    // @RolesAllowed("ADMIN")
    public Response deleteUser(@PathParam("levelId") String levelId) {
        levelService.deleteLevel(levelId);

        return Response.ok("Level successfully deleted!").build();
    }


}
