package variables;

import java.util.List;

public class StringResSet extends ComparableResSet<String>{

	public StringResSet(List<String> data) {
		super("String",data);
	}
	
	public StringResSet(String data) {
		super("String",data);
	}

	@Override
	protected ResSet getAsReset(String value) {
		return new StringResSet(value);
	}

	@Override
	public String getJSONType() {
		return "string";
	}

	@Override
	protected ResSet getAsReset(List<String> value) {
		return new StringResSet(value);
	}

}
