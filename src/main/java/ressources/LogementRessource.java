package ressources;

import entities.Logement;
import filters.Secured;
import metiers.LogementBusiness;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/logements")
public class LogementRessource {
    public static LogementBusiness LogB = new LogementBusiness();

    // POST to add a new logement
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addLog(Logement Log) {
        if (LogB.addLogement(Log)) {
            return Response.status(Response.Status.CREATED).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    // GET to retrieve all logements
    @Secured
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Logement> getLogs() {
        return LogB.getLogements();
    }

    // PUT to update an existing logement
    @PUT
    @Path("/{reference}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateLogement(@PathParam("reference") int reference, Logement logement) {
        boolean updated = LogB.updateLogement(reference, logement);
        if (updated) {
            return Response.ok("Logement updated successfully").build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Logement not found").build();
        }
    }

    // DELETE to remove a logement by reference
    @DELETE
    @Path("/{reference}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteLogement(@PathParam("reference") int reference) {
        boolean deleted = LogB.deleteLogement(reference);
        if (deleted) {
            return Response.ok("Logement deleted successfully").build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Logement not found").build();
        }
    }
}
