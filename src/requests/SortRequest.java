package requests;

import interfaces.Request;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import variables.ComparableResSet;
import variables.NullResSet;
import variables.ResSet;
import variables.StringResSet;
import builder.RequestBuilder;
import error.ResSetException;

public class SortRequest extends Request {
	private boolean descendent;
	public SortRequest(ResSet input) {
		this(input,false);
	}
	
	public SortRequest(ResSet input, boolean descendent) {
		super(input);
		this.descendent = descendent;
	}

	@Override
	public ResSet execute() throws ResSetException {
		try{
			ComparableResSet<?> a = (ComparableResSet<?>)input;
			return a.sort(descendent);
		}catch(Exception e){
			return new NullResSet(input.size());
		}
	}
	
	private void setDescendent(boolean b){
		this.descendent=b;
	}

	@Override
	public ResSet getInputFromRawData(JSONObject rawData, RequestBuilder builder) throws JSONException,
			ResSetException {
		
		if(rawData.optJSONObject("des")==null){
			this.setDescendent(true);
		}
		else{
			JSONObject des = rawData.getJSONObject("des");
			Request desRequest = builder.build(des);
			
			boolean b = Boolean.valueOf(desRequest.execute().get(0).toString());
			this.setDescendent(b);
		}
		
		ResSet a;
		if(rawData.optJSONArray("vals")==null){
			//vals must be a query
			a = builder.build(rawData.getJSONObject("vals")).execute();			
		}
		else{
			JSONArray vals = rawData.getJSONArray("vals");
			List<String> valsRes = new ArrayList<>();
			for(int i=0; i<vals.length();i++){
				JSONObject arg1JSON = vals.getJSONObject(i);
				
				Request arg1Request = builder.build(arg1JSON);
				
				ResSet arg1 = arg1Request.execute();
				valsRes.add(arg1.get(0).toString());
			}
			
			a = new StringResSet(valsRes);
		}
		return a;
	}

}
