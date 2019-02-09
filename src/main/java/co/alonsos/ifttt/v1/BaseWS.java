package co.alonsos.ifttt.v1;

import java.io.InputStream;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MultivaluedMap;
import org.apache.log4j.Logger;
import com.google.gson.Gson;
import co.alonsos.ifttt.obj.S_Response;
import co.alonsos.java_utilities.io.IO_Utils;
import co.alonsos.java_utilities.rest.ResponseConstants;


public class BaseWS {
	private static Logger log = Logger.getLogger(BaseWS.class);
	private static String keyName = "iftttServiceKey";
	protected S_Response res = new S_Response();
	protected Gson gson = new Gson();
	protected IO_Utils io = new IO_Utils();
	private static String serviceKey;

	public BaseWS() {
		//try to load the service key from either properties or env vars
		if (System.getProperty(keyName) != null) {
			setServiceKey(System.getProperty(keyName));
		}else if (System.getenv(keyName) != null) {
			setServiceKey(System.getenv(keyName));
		}else {
			log.error("No IFTTT service key was found in either a system property or environment variable");
			log.error("Please setup the KV as iftttServiceKey=<ifttt service key value>");
			System.exit(1);
		}
	}

	private void setServiceKey(String key) {
		if (serviceKey == null) {
			serviceKey = key;
		}
	}

	protected String validateInputReturnString(InputStream input) throws Exception {
		String inputFile;
		if (input == null) {
			throw new Exception(ResponseConstants.INVALID_INPUT);
		}
		try {
			inputFile = io.StreamToString(input);
			if (inputFile.isEmpty()) {
				throw new Exception(ResponseConstants.INVALID_INPUT);
			}
			return inputFile;
		}catch (Exception e) {
			throw new Exception(e.getMessage());
		}finally {
			inputFile = null;
		}
	}

	protected boolean isAsync(MultivaluedMap<String, String> queryStrings) {
		if (queryStrings != null) {
			return (queryStrings.size() > 0 && queryStrings.get("async") != null
			        && queryStrings.get("async").get(0).equals("true"));
		}else {
			return false;
		}
	}

	protected boolean validHeaderKey(HttpHeaders inputHeaders, String header) {
		String reqHeaderKey = "";
		if (inputHeaders.getRequestHeader(header).get(0) != null) {
			reqHeaderKey = inputHeaders.getRequestHeader(header).get(0);
		}else {
			log.debug("The request header (" + header + ") is missing.");
			return false;
		}
		if (serviceKey.equals(reqHeaderKey)) {
			return true;
		}else {
			log.debug("Application Service Key: " + serviceKey + " does not match the key in the request header ("
			        + header + "): " + reqHeaderKey);
			return false;
		}

	}
}
