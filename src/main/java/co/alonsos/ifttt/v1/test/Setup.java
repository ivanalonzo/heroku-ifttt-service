package co.alonsos.ifttt.v1.test;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import org.apache.log4j.Logger;
import co.alonsos.ifttt.obj.actions.CreateNewThing;
import co.alonsos.ifttt.v1.BaseWS;
import co.alonsos.java_utilities.rest.S_Response;

@Path("test")
public class Setup extends BaseWS {
	private static Logger log = Logger.getLogger(Setup.class);
	S_Response res = new S_Response();
	static final String name = "setup";

	@POST
	@Path(name)
	@Produces(MediaType.APPLICATION_JSON + "; charset=UTF-8")
	public Response doPost(@Context UriInfo params, @Context HttpHeaders reqHeaders) {

		log.debug(params.getPath());
		String msg = "Welcome to IFTTT Service. If you see this, the application is up and running";
		if (validHeaderKey(reqHeaders, "Ifttt-Service-Key")) {
			return res.makeOK(msg, new CreateNewThing());
		}else {
			// return res.makeException(null, 401, null);
			return Response.status(401).entity(gson.toJson(res)).build();

		}

	}

}
