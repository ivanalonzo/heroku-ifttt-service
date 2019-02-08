package co.alonsos.ifttt.v1.triggers;

import java.io.IOException;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import org.apache.log4j.Logger;
//import org.glassfish.jersey.media.multipart.FormDataParam;
import co.alonsos.ifttt.v1.BaseWS;

@Path("trigger")
public class NewThingCreated extends BaseWS {
	private static Logger log = Logger.getLogger(NewThingCreated.class);
	String msg = "";

	@POST
	@Path("new_thing_created")
	@Consumes({
	        MediaType.APPLICATION_JSON
	})
	@Produces(MediaType.APPLICATION_JSON + "; charset=UTF-8")
	public Response doPost(@Context UriInfo params) throws IOException {
		MultivaluedMap<String, String> queryStrings = params.getQueryParameters(true);
		try {
		}catch (Exception e) {
			log.error(e.getMessage(), e);
			return res.makeException(e.getMessage(), 400, null);
		}finally {

		}
		return res.makeOK(msg, null);
	}
}
