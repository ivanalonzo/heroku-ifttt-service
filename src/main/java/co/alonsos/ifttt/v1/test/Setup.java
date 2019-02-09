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

@Path("test")
public class Setup extends BaseWS {
	private static Logger log = Logger.getLogger(Setup.class);
	static final String name = "setup";

	@POST
	@Path(name)
	@Produces(MediaType.APPLICATION_JSON + "; charset=UTF-8")
	public Response doPost(@Context UriInfo params, @Context HttpHeaders reqHeaders, String body) {
		log.debug("This is supposed to setup some type of test. You could choose to use a real");
		log.debug("CreateNewThing object and have it perform some function, though in this example that would");
		log.debug("be pointless since CreateNewThing does nothing at the moment");
		CreateNewThing action = new CreateNewThing(null);
		action.doSomeAction();

		String msg = "Welcome to IFTTT Service. If you see this, the application is up and running";
		if (validHeaderKey(reqHeaders, "Ifttt-Service-Key")) {
			return res.makeOK(msg, action);
		}else {
			return res.makeException(null, 401, null);
		}

	}

}
