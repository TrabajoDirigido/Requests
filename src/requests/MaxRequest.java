package requests;

import org.json.JSONException;
import org.json.JSONObject;

import builder.RequestBuilder;

import variables.ComparableResSet;
import variables.NullResSet;
import variables.ObjectResSet;
import variables.ResSet;
import error.ResSetException;
import interfaces.Request;

public class MaxRequest extends Request {

	public MaxRequest(ResSet input) {
		super(input);
	}

	@Override
	public ResSet execute() throws ResSetException {
		try{
			ComparableResSet<?> a = (ComparableResSet<?>)input;
			return a.getMax();
		}catch(Exception e){
			return new ObjectResSet(new NullResSet(1));
		}
	}

	@Override
	public ResSet getInputFromRawData(JSONObject rawData, RequestBuilder builder) throws JSONException,
			ResSetException {
		
		return builder.build(rawData.getJSONObject("vals")).execute();
	}

}
