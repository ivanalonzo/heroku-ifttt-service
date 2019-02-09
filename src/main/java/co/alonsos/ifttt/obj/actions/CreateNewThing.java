package co.alonsos.ifttt.obj.actions;

import co.alonsos.ifttt.obj.IftttReq;

public class CreateNewThing {

	public int id;
	public String url;
	private transient IftttReq body;

	/**
	 * Basic constructor for the action. In practice the class should do
	 * something with the request it ws provided
	 * 
	 * @param request
	 */
	public CreateNewThing(IftttReq request) {
		body = request;
	}

	/**
	 * Simply sets the values for ID and URL
	 */
	public void doSomeAction() {
		id = this.getClass().hashCode();
		url = "https://katchup.news";
	}
}
