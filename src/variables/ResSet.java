package variables;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;

public abstract class ResSet{
	
	private String type = "NullType";
	protected List<Object> data;
	
	public <E> ResSet(String type, List<E> data){
		this.type = type;
		this.data = new ArrayList<>();
		if(data!=null){
			for(E e : data){
				this.data.add(e);
			}
		}
	}
	
	public int size(){
		return data.size();
	}
	
	public List<Object> getIterator(){
		return data;
	}
	
	public String getDataType(){
		return type;
	}
	
	public Object get(int pos){
		return this.data.get(pos);
	}

	@Override
	public boolean equals(Object object) {
		if(object instanceof ResSet){
			ResSet other = (ResSet)object;
			if(other.getDataType().equals(this.getDataType()) && other.size()==this.size()){
				for(Object o : this.getIterator()){
					if(!other.getIterator().contains(o)){
						return false;
					}
				}
				return true;
			}
		}
		return false;
	}
	
	public String toString(){
		return getIterator().toString();
	}
	
	public abstract JSONArray toJSONArray();

	public abstract ResSet filterEqual(Object toCompare);
	public abstract ResSet filterNotEqual(Object toCompare);

}
