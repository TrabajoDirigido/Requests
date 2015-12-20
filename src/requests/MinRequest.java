package requests;

import interfaces.Request;

import org.json.JSONException;
import org.json.JSONObject;

import variables.ComparableResSet;
import variables.NullResSet;
import variables.ObjectResSet;
import variables.ResSet;
import builder.RequestBuilder;
import error.ResSetException;

public class MinRequest extends Request{

	public MinRequest(ResSet input) {
		super(input);
	}

	@Override
	public ResSet execute() throws ResSetException {
		try{
			ComparableResSet<?> a = (ComparableResSet<?>)input;
			return a.getMin();
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
