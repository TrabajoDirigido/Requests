package variables;

import java.util.List;

public class IntegerResSet extends ComparableResSet<Integer>{

	public IntegerResSet(List<Integer> data) {
		super("Integer",data);
	}
	
	public IntegerResSet(Integer data) {
		super("Integer",data);
	}

	@Override
	protected ResSet getAsReset(Integer value) {
		return new IntegerResSet(value);
	}

	@Override
	public String getJSONType() {
		return "int";
	}

	@Override
	protected ResSet getAsReset(List<Integer> value) {
		return new IntegerResSet(value);
	}

}
