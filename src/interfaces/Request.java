package interfaces;

import org.json.JSONException;
import org.json.JSONObject;

import builder.RequestBuilder;

import error.ResSetException;
import variables.ResSet;

public abstract class Request{
	protected ResSet input;
	private int id = -1;

	public Request(ResSet input){
		setInput(input);
	}
	
	public void setInput(ResSet newInput){
		this.input = newInput;
	}
	
	public abstract ResSet execute() throws ResSetException;
	public abstract ResSet getInputFromRawData(JSONObject rawData, RequestBuilder requestBuilder) throws JSONException, ResSetException;

	public void setId(int id) {
		this.id = id;		
	}
	
	public int getId(){
		return this.id;
	}
}
