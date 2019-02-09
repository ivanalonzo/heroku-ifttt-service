package co.alonsos.ifttt.v1;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import org.apache.log4j.Logger;

@Path("status")
public class Status extends BaseWS {
	private static Logger log = Logger.getLogger(Status.class);

	@GET
	@Produces(MediaType.APPLICATION_JSON + "; charset=UTF-8")
	public Response doGet(@Context UriInfo params, @Context HttpHeaders reqHeaders) {
		String msg = "Welcome to IFTTT Service. If you see this, the application is up and running";

		log.debug("Confirm the service key was sent in the header and respond to the request");
		if (validHeaderKey(reqHeaders, "Ifttt-Service-Key")) {
			return res.makeOK(msg, null);
		}else {
			return res.makeException(null, 401, null);
		}
	}
}
