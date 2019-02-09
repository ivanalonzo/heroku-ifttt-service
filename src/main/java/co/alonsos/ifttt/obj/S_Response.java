package co.alonsos.ifttt.obj;

import javax.ws.rs.core.Response;
import org.apache.log4j.Logger;
import com.google.gson.Gson;

public class S_Response {
	Gson gson = new Gson();
	private static Logger log = Logger.getLogger(S_Response.class);

	/**
	 * Generates a javax Response object.
	 * 
	 * @param msg:
	 *        If null, will use OK
	 * @param data:
	 *        Can be null
	 * @return
	 */
	public Response makeOK(String msg, Object data) {
		ResObject res = new ResObject();
		res.status_code = 200;
		if (msg == null || msg.isEmpty()) {
			res.status_msg = "OK";
		}else {
			res.status_msg = msg;
		}

		if (data != null) {
			res.data = data;
		}
		return Response.ok(gson.toJson(res)).build();
	}

	/**
	 * Generates an exception javax Response object
	 * 
	 * @param msg:
	 *        if null, it will use "Exception" in the message
	 * @param code:
	 *        Code must be in the 400 range. If not, it will default to 400
	 * @param data:
	 *        can be null
	 * @return
	 */
	public Response makeException(String msg, int code, Object data) {
		ErrObject res = new ErrObject();
		res.status_code = code;
		if (msg == null || msg.isEmpty()) {
			res.status_msg = "Exception";
		}else {
			res.status_msg = msg;
		}
		if (code < 400 && code > 400) {
			res.status_code = 400;
		}
		if (data != null) {
			res.errors = data;
		}
		return Response.status(res.status_code).entity(gson.toJson(res)).build();
	}

	class ResObject {
		int status_code;
		String status_msg;
		Object data;
	}

	class ErrObject {
		int status_code;
		String status_msg;
		Object errors;
	}
}

