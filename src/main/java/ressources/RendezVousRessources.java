package ressources;

import entities.RendezVous;
import filters.Secured;
import metiers.RendezVousBusiness;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/rdv")
public class RendezVousRessources {

    public static RendezVousBusiness rendezVousBusiness = new RendezVousBusiness();

    // POST to add a new rendezvous
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addRendezVous(RendezVous rendezVous) {
        if (rendezVousBusiness.addRendezVous(rendezVous)) {
            return Response.status(Response.Status.CREATED).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    // GET to retrieve all rendezvous
    @Secured
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<RendezVous> getListeRendezVous() {
        return rendezVousBusiness.getListeRendezVous();
    }

    // PUT to update an existing rendezvous
    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateRendezVous(@PathParam("id") int id, RendezVous updatedRendezVous) {
        boolean updated = rendezVousBusiness.updateRendezVous(id, updatedRendezVous);
        if (updated) {
            return Response.ok("RendezVous updated successfully").build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("RendezVous not found").build();
        }
    }

    // DELETE to remove a rendezvous by id
    @DELETE
    @Path("/{id}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteRendezVous(@PathParam("id") int id) {
        boolean deleted = rendezVousBusiness.deleteRendezVous(id);
        if (deleted) {
            return Response.ok("RendezVous deleted successfully").build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("RendezVous not found").build();
        }
    }
}
