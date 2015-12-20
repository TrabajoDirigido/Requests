package variables;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public abstract class ComparableResSet<E extends Comparable<E>> extends ResSet{
	
	List<E> set;

	public ComparableResSet(String type, List<E> data) {
		super(type, data);
		this.set = data;
	}
	
	public ComparableResSet(String type, E o) {
		super(type, Arrays.asList(o));
		this.set = new ArrayList<>();
		this.set.add(o);
	}
	
	@Override
	public List<Object> getIterator(){
		List<Object> objects = new ArrayList<>();
		for(E e : set){
			objects.add(e);
		}
		return objects;
	}
	
	public ComparableResSet<E> sort(final boolean descendent){
		this.set.sort(new Comparator<E>() {
			@Override
			public int compare(E arg0, E arg1) {
				return descendent?arg1.compareTo(arg0):arg0.compareTo(arg1);
			}			
		});
		return this;
	}
	
	public ResSet getMin(){
		this.sort(false);
		return this.getAsReset(set.get(0));
	}
	
	protected abstract ResSet getAsReset(E value);
	protected abstract ResSet getAsReset(List<E> value);
	
	public ResSet getMax(){
		this.sort(true);
		return this.getAsReset(set.get(0));
	}
	
	@Override
	public int size() {
		return this.set.size();
	}
	
	public E getE(int pos){
		return this.set.get(pos);
	}
	
	public List<E> getEIterator(){
		return this.set;
	}
	
	public abstract String getJSONType();
	
	public JSONArray toJSONArray(){		
		String type = getJSONType();
		JSONArray array = new JSONArray();
		for(E e : set){
			if(e==null)
				array.put(new JSONObject().put("type", "null"));
			else
				array.put(new JSONObject().put("var", e).put("type", type));
		}		
		return array;		
	}
	
	public ResSet filterEqual(Object toCompare){
		ArrayList<E> set = new ArrayList<>();
		for(E e : this.set){
			if((e==null && toCompare==null) || (e!=null && e.equals(toCompare))){
				set.add(e);
			}
		}
		return this.getAsReset(set);
	}
	public ResSet filterNotEqual(Object toCompare){
		ArrayList<E> set = new ArrayList<>();
		for(E e : this.set){
			if((e==null && toCompare!=null) || (e!=null && !e.equals(toCompare))){
				set.add(e);
			}
		}
		return this.getAsReset(set);
	}

}
