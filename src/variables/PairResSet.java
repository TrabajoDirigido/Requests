package variables;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.json.JSONArray;

import javafx.util.Pair;

public class PairResSet<K, V> extends ResSet{
	
	private List<Pair<K,V>> set;

	public PairResSet(List<Pair<K,V>> data) {
		super("Pair",data);
		this.set = data;
	}
	
	public PairResSet(Pair<K,V> data) {
		super("Pair",Arrays.asList(data));
		this.set = new ArrayList<>();
		this.set.add(data);
	}

	public List<Pair<K,V>> getPairIterator() {
		return this.set;
	}
	
	public Object getPair(int pos) {
		return this.set.get(pos);
	}

	@Override
	public JSONArray toJSONArray() {
		return new JSONArray();
	}

	@Override
	public ResSet filterEqual(Object toCompare) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResSet filterNotEqual(Object toCompare) {
		// TODO Auto-generated method stub
		return null;
	}

}
