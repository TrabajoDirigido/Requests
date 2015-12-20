package requests;

import org.json.JSONException;
import org.json.JSONObject;

import builder.RequestBuilder;

import error.ResSetException;
import variables.IntegerResSet;
import variables.ResSet;
import interfaces.Request;

public class CountRequest extends Request{

	public CountRequest(ResSet input) {
		super(input);
	}

	@Override
	public ResSet execute() throws ResSetException {
		return new IntegerResSet(input.size());
	}

	@Override
	public ResSet getInputFromRawData(JSONObject rawData, RequestBuilder builder) throws JSONException,
			ResSetException {
		return builder.build(rawData.getJSONObject("vals")).execute();
	}
	
}
