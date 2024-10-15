package ressources;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import entities.Credentials;


@Path("authentication")
public class AuthenticatewCredentials {

    @POST
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response authenticateUser(Credentials cred) {
        try {
            // Call the authenticate method
            if (authenticate(cred.getUsername(), cred.getPassword())) {
                return Response.ok("Authentication successful").build();
            } else {
                return Response.status(Response.Status.UNAUTHORIZED).entity("Authentication failed").build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Internal server error").build();
        }
    }

    // Define the authenticate method
    private boolean authenticate(String username, String password) {
        // Example logic for authentication
        // This could be replaced by database validation or other logic
        String expectedUsername = "admin";
        String expectedPassword = "password123";

        return username.equals(expectedUsername) && password.equals(expectedPassword);
    }
}
