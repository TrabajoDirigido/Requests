package requests;

import interfaces.Request;

import org.json.JSONException;
import org.json.JSONObject;

import variables.BooleanResSet;
import variables.DoubleResSet;
import variables.IntegerResSet;
import variables.NullResSet;
import variables.ObjectResSet;
import variables.ResSet;
import variables.StringResSet;
import builder.RequestBuilder;
import error.ResSetException;

public class NullRequest extends Request{

	public NullRequest(ResSet input) {
		super(input);
	}

	@Override
	public ResSet execute() throws ResSetException {
		return input;
	}

	@Override
	public ResSet getInputFromRawData(JSONObject rawData, RequestBuilder builder) throws JSONException,
			ResSetException {
		
		switch(rawData.getString("type")){
		case "string":
			return new StringResSet(rawData.getString("var"));
		case "float":
			return new DoubleResSet(rawData.getDouble("var"));
		case "int":
			return new IntegerResSet(rawData.getInt("var"));
		case "bool":
			return new BooleanResSet(rawData.getBoolean("var"));
		case "null":
			return new NullResSet(1);
		}
		
		return new ObjectResSet(rawData.get("var"));
	}

}
