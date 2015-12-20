package requests;

import interfaces.Request;

import java.util.ArrayList;
import java.util.List;

import javafx.util.Pair;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import variables.DoubleResSet;
import variables.IntegerResSet;
import variables.PairResSet;
import variables.ResSet;
import builder.RequestBuilder;
import error.ResSetException;

public class GetRequest extends Request{

	public GetRequest(ResSet input) {
		super(input);
	}

	@Override
	public ResSet execute() throws ResSetException {
		if(!input.getDataType().equals("Pair")){
			throw new ResSetException("Get Request Data is not a "+Pair.class.toString()+", instead: "+input.getDataType());
		}
		
		@SuppressWarnings("unchecked")
		PairResSet<Integer, Integer> dataSet = (PairResSet<Integer, Integer>)input;
		
		List<Double> res = new ArrayList<>();
		for(Pair<Integer,Integer> data : dataSet.getPairIterator()){
			//TODO
			Double b = (data.getKey()*data.getValue())*1.0;
			res.add(b);
		}
		
		return new DoubleResSet(res);
	}

	@Override
	public ResSet getInputFromRawData(JSONObject rawData, RequestBuilder builder) throws JSONException, ResSetException {
		
		JSONArray xs = rawData.getJSONArray("x");
		JSONArray ys = rawData.getJSONArray("y");
		
		List<Pair<Integer,Integer>> res = new ArrayList<>();
		for(int i=0; i<xs.length();i++){
			JSONObject xJSON = xs.getJSONObject(i);
			JSONObject yJSON = ys.getJSONObject(i);
			
			Request xRequest = builder.build(xJSON);
			Request yRequest = builder.build(yJSON);
			
			IntegerResSet xSet = (IntegerResSet) xRequest.execute();
			IntegerResSet ySet = (IntegerResSet) yRequest.execute();
			
			int x = xSet.getE(0);
			int y = ySet.getE(0);
			
			Pair<Integer,Integer> p = new Pair<>(x, y);
			res.add(p);
		}
		
		
		return new PairResSet<Integer,Integer>(res);
	}

	

}

