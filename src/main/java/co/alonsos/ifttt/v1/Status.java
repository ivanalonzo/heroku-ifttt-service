package co.alonsos.ifttt.v1;

import java.util.Properties;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import org.apache.log4j.Logger;
import co.alonsos.java_utilities.rest.S_Response;

@Path("status")
public class Status extends BaseWS {
	private static Logger log = Logger.getLogger(Status.class);
	S_Response res = new S_Response();

	@GET
	@Produces(MediaType.APPLICATION_JSON + "; charset=UTF-8")
	public Response doGet(@Context UriInfo params, @Context HttpHeaders reqHeaders) {
//		String apiKey = System.getProperty("key");
		String apiKey = "VMKOeiRtf8p2TltpJvVGeLASTAByW9vX_lggRE7W9EEEPLZKklC0RcSOJ4KR9AD5";
		setServiceKey(apiKey);

		log.debug(params.getPath());
		String msg = "Welcome to IFTTT Service. If you see this, the application is up and running";
		if (validHeaderKey(reqHeaders, "Ifttt-Service-Key")) {
			return res.makeOK(msg, null);
		}else {
			return res.makeException(null, 401, null);
		}

	}

}
