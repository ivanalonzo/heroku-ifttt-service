package co.alonsos.heroku;

import org.apache.log4j.Logger;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.util.preventers.AppContextLeakPreventer;
import org.eclipse.jetty.util.preventers.GCThreadLeakPreventer;
import org.eclipse.jetty.util.preventers.Java2DLeakPreventer;
import org.eclipse.jetty.util.preventers.SecurityProviderLeakPreventer;
import org.eclipse.jetty.webapp.WebAppContext;

public class Main {

	static Server server;
	static WebAppContext root;
	static Logger log = Logger.getLogger(Main.class);

	public static void main(String[] args) {
		try {
			// The port that we should run on can be set into an environment variable
			// Look for that variable and default to 8080 if it isn't there.
			String webPort = System.getenv("PORT");
			if (webPort == null || webPort.isEmpty()) {
				webPort = "8080";
			}
			startServer(webPort);
			log.debug("Server has started and joined, using port: " + webPort);
		}catch (Exception e) {
			log.error(e.getMessage(), e);
			stopServer();
		}
	}

	public static void startServer(String webPort) throws Exception {

		server = new Server(Integer.valueOf(webPort));
		server.addBean(new AppContextLeakPreventer());
		server.addBean(new GCThreadLeakPreventer());
		server.addBean(new SecurityProviderLeakPreventer());
		server.addBean(new Java2DLeakPreventer());

		root = new WebAppContext();

		root.setContextPath("/");
		// Parent loader priority is a class loader setting that Jetty accepts.
		// By default Jetty will behave like most web containers in that it will
		// allow your application to replace non-server libraries that are part of the
		// container. Setting parent loader priority to true changes this behavior.
		// Read more here: http://wiki.eclipse.org/Jetty/Reference/Jetty_Classloading
		root.setParentLoaderPriority(true);

		final String webappDirLocation = "src/main/webapp/";
		root.setDescriptor(webappDirLocation + "/WEB-INF/web.xml");
		root.setResourceBase(webappDirLocation);
		server.setHandler(root);
		server.start();
		server.join();
	}

	public static void stopServer() {
		try {
			server.stop();
			server.destroy();
		}catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}
}

