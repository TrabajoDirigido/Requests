package variables;

import java.util.List;

public class DoubleResSet extends ComparableResSet<Double>{

	public DoubleResSet(List<Double> data) {
		super("Double",data);
	}
	
	public DoubleResSet(Double data) {
		super("Double",data);
	}

	@Override
	protected ResSet getAsReset(Double value) {
		return new DoubleResSet(value);
	}

	@Override
	public String getJSONType() {
		return "float";
	}

	@Override
	protected ResSet getAsReset(List<Double> value) {
		return new DoubleResSet(value);
	}

}
