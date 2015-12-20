package variables;

import java.util.ArrayList;

public class NullResSet extends ObjectResSet{
	
	public NullResSet(int size) {
		super(null);
		data = new ArrayList<>();
		for(int x = 0; x<size;x++){
			data.add(null);
		}
	}

}
