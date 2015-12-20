package variables;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;


public class ObjectResSet extends ResSet {

	public <E> ObjectResSet(List<E> data) {
		super("Object",data);
	}
	
	public <E> ObjectResSet(E data) {
		super("Object",Arrays.asList(data));
	}

	@Override
	public JSONArray toJSONArray() {
		JSONArray array = new JSONArray();
		
		for(Object o : this.getIterator()){
			if(o==null)
				array.put(new JSONObject().put("type", "null"));
			else
				array.put(new JSONObject().put("type", getType(o)).put("var", o));
		}
		return array;
	}
	
	private String getType(Object o){
		if(o instanceof String){
			return "string";
		}
		else if(o instanceof Boolean){
			return "bool";
		}
		else if(o instanceof Double){
			return "float";
		}
		else if(o instanceof Integer){
			return "int";
		}
		else if(o instanceof ResSet){
			return null;
		}
		return null;
	}

	@Override
	public ResSet filterEqual(Object toCompare) {
		List<Object> set = new ArrayList<>();
		
		for(Object o : this.getIterator()){
			if((o==null && toCompare==null) || (o!=null && o.equals(toCompare))){
				set.add(o);
			}
		}
		
		return new ObjectResSet(set);
	}

	@Override
	public ResSet filterNotEqual(Object toCompare) {
		List<Object> set = new ArrayList<>();
		
		for(Object o : this.getIterator()){
			if((o==null && toCompare!=null) || (o!=null && !o.equals(toCompare))){
				set.add(o);
			}
		}
		
		return new ObjectResSet(set);
	}

}
