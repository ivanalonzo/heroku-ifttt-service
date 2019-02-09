package co.alonsos.ifttt.obj.triggers;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import co.alonsos.ifttt.obj.Meta;

public class NewThingCreated {

	public String created_at;
	public Meta meta;

	public NewThingCreated(Date d) {
		// Input
		Date date = d;

		// Conversion
		SimpleDateFormat sdf;
		sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
		sdf.setTimeZone(TimeZone.getTimeZone("PST"));
		created_at = sdf.format(date);

		meta = new Meta(d);
	}


}
