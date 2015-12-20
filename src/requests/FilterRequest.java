package requests;

import interfaces.Request;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import variables.DoubleResSet;
import variables.ResSet;
import builder.RequestBuilder;
import error.ResSetException;

public class FilterRequest extends Request{

	private boolean equal = true;
	private Object toCompare = null;
	
	public FilterRequest(ResSet input) {
		super(input);
	}
	
	public void setEqual(boolean b){
		this.equal = b;
	}
	
	public void setToCompare(Object o){
		this.toCompare = o;
	}
	

	@Override
	public ResSet execute() throws ResSetException {		
		if(this.equal){
			return this.input.filterEqual(this.toCompare);
		}
		return this.input.filterNotEqual(this.toCompare);
	}

	@Override
	public ResSet getInputFromRawData(JSONObject rawData,
			RequestBuilder requestBuilder) throws JSONException,
			ResSetException {
		
		String type = rawData.getString("type");
		this.setEqual(type.equals("equal"));
		
		ResSet toCompare = requestBuilder.build(rawData.getJSONObject("var")).execute();
		
		this.setToCompare(toCompare.get(0));

		ResSet resSet = requestBuilder.build(rawData.getJSONObject("vals")).execute();
		
		
		return resSet;
	}
	
	public static void main(String[] args) throws ResSetException {
		List<Double> list = new ArrayList<>();
		list.add(3.0);
		list.add(1.0);
		list.add(null);
		list.add(5.0);
		
		FilterRequest r = new FilterRequest(new DoubleResSet(list));
		r.setEqual(false);
		r.setToCompare(null);
		
		System.out.println(r.execute().getDataType());
		System.out.println(r.execute().toString());
		
		CountRequest count = new CountRequest(r.execute());
		System.out.println(count.execute().toString());
		
	}

}
