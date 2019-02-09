package co.alonsos.ifttt.obj;

import java.util.Date;

public class Meta {

	public long id;
	public long timestamp;

	public Meta(Date d) {
		id = d.getTime();
		timestamp = d.getTime() / 1000;
	}
}
