package co.alonsos.ifttt.obj;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IftttReq {

	ActionFields actionFields;
	User user;
	IftttSource ifttt_source;
	int limit = 10;

}
