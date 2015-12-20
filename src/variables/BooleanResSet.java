package variables;

import java.util.List;

public class BooleanResSet extends ComparableResSet<Boolean> {

	public BooleanResSet(List<Boolean> data) {
		super("Boolean",data);
	}
	
	public BooleanResSet(Boolean data) {
		super("Boolean",data);
	}

	@Override
	protected ResSet getAsReset(Boolean value) {
		return new BooleanResSet(value);
	}

	@Override
	public String getJSONType() {
		return "bool";
	}

	@Override
	protected ResSet getAsReset(List<Boolean> value) {
		return new BooleanResSet(value);
	}
}
