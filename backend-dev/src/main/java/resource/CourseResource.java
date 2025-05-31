package resource;

import java.util.List;

import dto.CourseDTO;
import entity.Course;
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

    @GET
    @Path("/{courseId}")
    public Response getCourseById(@PathParam("courseId") String courseId) {
        CourseDTO courseDTO = courseService.getCourseById(courseId);

        if (courseDTO == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Course not found!").build();
        }

        return Response.status(Response.Status.OK).entity(courseDTO).build();
    }

    @POST
    public Response addCourse(CourseDTO courseDTO) {

        CourseDTO courseDTOs = courseService.addCourse(courseDTO);

        return Response.status(Response.Status.CREATED).entity(courseDTOs).build();
    }
    
    @PATCH
    @Path("/{courseId}")
    public Response updateCourse(@PathParam("courseId") String courseId, CourseDTO courseDTO) {
        Course updatedCourse = courseService.updateCourse(courseId, courseDTO);
        
        return Response.status(Response.Status.OK).entity(new CourseDTO(updatedCourse)).build();
    }

    @DELETE
    @Path("/{courseId}")
    public Response deleteCourse(@PathParam("courseId") String courseId) {
        courseService.deleteCourse(courseId);
        
        return Response.ok("Course successfully deleted!").build();
    }
}
