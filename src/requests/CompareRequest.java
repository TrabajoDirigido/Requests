package requests;

import interfaces.Request;

import java.util.ArrayList;
import java.util.List;

import javafx.util.Pair;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cell_list.CellListException;
import cell_list.DefaultCellList;
import cell_list.ExcelCellList;
import cell_list.PuntualCellList;

import variables.BooleanResSet;
import variables.ObjectResSet;
import variables.PairResSet;
import variables.ResSet;
import builder.RequestBuilder;
import error.ResSetException;

public class CompareRequest extends Request{

	public CompareRequest(ResSet input) {
		super(input);
	}

	@Override
	public ResSet execute() throws ResSetException {
		if(!input.getDataType().equals("Pair")){
			throw new ResSetException("CompareRequest", input);
		}
		
		List<Boolean> res = new ArrayList<>();
		@SuppressWarnings("unchecked")
		PairResSet<Object, Object> dataSet = (PairResSet<Object, Object>)input;
		
		for(Pair<Object,Object> data : dataSet.getPairIterator()){
			if(data.getKey()==null){
				res.add(false);
				continue;
			}
			boolean b = data.getKey().equals(data.getValue());
			res.add(b);
		}
		
		return new BooleanResSet(res);
	}
	
	private ResSet getResSet(JSONObject rawData, String key, RequestBuilder builder) throws JSONException, ResSetException{
		ResSet a;
		if(rawData.optJSONArray(key)==null){
			//arg1 must be a query
			a = builder.build(rawData.getJSONObject(key)).execute();			
		}
		else{
			JSONArray arg = rawData.getJSONArray(key);
			List<Object> argRes = new ArrayList<>();
			for(int i=0; i<arg.length();i++){
				JSONObject argJson = arg.getJSONObject(i);
				
				Request argReq = builder.build(argJson);
				
				ResSet arg1 = argReq.execute();
				argRes.add(arg1.get(0));
			}
			
			a = new ObjectResSet(argRes);
		}
		return a;
	}

	private ExcelCellList<Object> getCellList(ResSet a){		
		if(a.size()==1){
			return new PuntualCellList<Object>(a.get(0));
		}
		
		return new DefaultCellList<Object>(a.getIterator());
	}
	
	@Override
	public ResSet getInputFromRawData(JSONObject rawData, RequestBuilder builder) throws JSONException,
			ResSetException {
		
		ResSet a = getResSet(rawData, "arg1", builder);
		ResSet b = getResSet(rawData, "arg2", builder);
		
		if(a.size()!=b.size() && a.size()!=1 && b.size()!=1){
			throw new ResSetException("Compare 2 ResSet of different sizes");
		}
		
		ExcelCellList<Object> x = getCellList(a);
		ExcelCellList<Object> y = getCellList(b);
		
		List<Pair<Object,Object>> res = new ArrayList<>();
		try {
			res = x.getPairs(y);
		} catch (CellListException e) {
			e.printStackTrace();
		}
		
		return new PairResSet<Object, Object>(res);
	}

	

}
