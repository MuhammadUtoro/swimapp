package resource;

import java.net.ResponseCache;
import java.util.List;

import dto.CourseDTO;
import entity.Course;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import service.CourseService;

@Path("courses")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CourseResource {

    @Inject
    CourseService courseService;

    @GET
    public Response getAllCourses() {
        
        List<CourseDTO> courseDTOs = courseService.getAllCourses();

        return Response.status(Response.Status.OK).entity(courseDTOs).build();
    }

    @POST
    public Response addCourse(CourseDTO courseDTO) {

        CourseDTO courseDTOs = courseService.addCourse(courseDTO);

        return Response.status(Response.Status.CREATED).entity(courseDTOs).build();
    }
}
