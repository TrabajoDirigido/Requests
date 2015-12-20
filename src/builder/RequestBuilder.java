package builder;

import interfaces.Request;

import org.json.JSONException;
import org.json.JSONObject;

import requests.AndRequest;
import requests.CompareRequest;
import requests.CountRequest;
import requests.FilterRequest;
import requests.GetRequest;
import requests.MaxRequest;
import requests.MinRequest;
import requests.NullRequest;
import requests.OrRequest;
import requests.SortRequest;
import error.ResSetException;

public class RequestBuilder {
	
	public RequestBuilder(){
	}
	
	public Request build(JSONObject data) throws JSONException, ResSetException{
		if(!data.has("method")){
			return getCompleteRequest(new NullRequest(null), data);
		}
		switch(data.getString("method")){
		case "get":
			return getCompleteRequest(new GetRequest(null), data);	
		case "compare":
			return getCompleteRequest(new CompareRequest(null), data);
		case "sort":
			return getCompleteRequest(new SortRequest(null), data);	
		case "logic":
			if(data.getString("type").equals("and")){
				return getCompleteRequest(new AndRequest(null), data);
			}
			if(data.getString("type").equals("or")){
				return getCompleteRequest(new OrRequest(null), data);
			}
		case "min":
			return getCompleteRequest(new MinRequest(null), data);	
		case "max":
			return getCompleteRequest(new MaxRequest(null), data);
		case "count":
			return getCompleteRequest(new CountRequest(null), data);
		case "filter":
			return getCompleteRequest(new FilterRequest(null), data);
		}
		return null;
	}
	
	protected Request getCompleteRequest(Request r, JSONObject data) throws JSONException, ResSetException{
		int id = data.optInt("id",-1);
		r.setInput(r.getInputFromRawData(data, this));
		r.setId(id);
		return r;
	}
}
