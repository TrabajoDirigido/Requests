package interfaces;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import variables.BooleanResSet;
import variables.ResSet;
import builder.RequestBuilder;
import error.ResSetException;

public abstract class LogicRequest extends Request {

	public LogicRequest(ResSet input) {
		super(input);
	}

	@Override
	public ResSet execute() throws ResSetException {
		if(!input.getDataType().equals("Boolean")){
			throw new ResSetException("AndRequest Data is not a "+Boolean.class.toString()+", instead: "+input.getDataType());
		}
		
		BooleanResSet booleans = (BooleanResSet)input;
		List<Boolean> res = new ArrayList<>();
		for(Boolean b : booleans.getEIterator()){
			res.add(b);			
		}
		
		return new BooleanResSet(reduce(res));
	}
	
	public abstract boolean reduce(List<Boolean> b);
	
	@Override
	public ResSet getInputFromRawData(JSONObject rawData, RequestBuilder builder) throws JSONException,
			ResSetException {
		
		if(rawData.optJSONArray("vals")==null){
			//the vals data is a request!
			Request r = builder.build(rawData.getJSONObject("vals"));
			
			//assume the request returns the correct input data 
			//ResSet<ResSet<Boolean>>
			return r.execute();		
			
		}
		//the vals data is an Array
		JSONArray input = rawData.getJSONArray("vals");
		List<Boolean> listRes = new ArrayList<>();
		for(int i=0; i<input.length();i++){
			JSONObject o = input.getJSONObject(i);
			Request r = builder.build(o);
			if(o.has("method")){
				//o is a request, assume it returns a ResSet<Boolean>
				listRes.add(Boolean.valueOf(r.execute().get(0).toString()));
			}
			else{
				//o is rawData! the builder returns a null request, must cast String result to boolean
				BooleanResSet b = (BooleanResSet)r.execute();
				listRes.add(b.getE(0));
			}
		}
		
		return new BooleanResSet(listRes);
	}

}
