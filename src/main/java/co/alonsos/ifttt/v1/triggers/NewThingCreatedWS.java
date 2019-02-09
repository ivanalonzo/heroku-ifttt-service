package co.alonsos.ifttt.v1.triggers;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import org.apache.log4j.Logger;
import co.alonsos.ifttt.obj.IftttReq;
import co.alonsos.ifttt.obj.triggers.NewThingCreated;
import co.alonsos.ifttt.v1.BaseWS;

@Path("triggers")
public class NewThingCreatedWS extends BaseWS {
	private static Logger log = Logger.getLogger(NewThingCreatedWS.class);
	String msg = "";
	static final String name = "new_thing_created";

	@POST
	@Path(name)
	@Produces(MediaType.APPLICATION_JSON + "; charset=UTF-8")
	public Response doPost(@Context UriInfo params, @Context HttpHeaders reqHeaders, String body) {
		List<NewThingCreated> response = new ArrayList<NewThingCreated>();;
		NewThingCreated trigger = null;

		if (body != null && !body.isEmpty()) {
			log.debug("Given the body, create an IFTTT Request Object and an action object");
			IftttReq bodyReq = gson.fromJson(body, IftttReq.class);
			if (bodyReq.getLimit() > 0) {
				for(int i = 0; i < bodyReq.getLimit(); i++) {
					trigger = new NewThingCreated(new Date(System.currentTimeMillis() - (i * 1000000)));
					log.debug("Create a response list of actions");
					response.add(trigger);
				}
			}
		}

		String msg = "Welcome to IFTTT Service. If you see this, the application is up and running";

		log.debug("Confirm the service key was sent in the header and respond to the request");
		if (validHeaderKey(reqHeaders, "Ifttt-Service-Key")) {
			return res.makeOK(msg, response);
		}else {
			log.debug("Errors have to come as an array, hence the List of KVs (map)");
			Map<String, String> error = new HashMap<String, String>();
			List<Map<String, String>> errors = new ArrayList<Map<String, String>>();
			error.put("message", "401");
			errors.add(error);
			return res.makeException(null, 401, errors);
		}
	}
}
