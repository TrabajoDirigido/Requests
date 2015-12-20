package test;
import static org.junit.Assert.assertEquals;
import interfaces.Request;

import java.util.ArrayList;
import java.util.List;

import javafx.util.Pair;

import org.json.JSONObject;
import org.junit.Test;

import requests.CompareRequest;
import variables.BooleanResSet;
import variables.PairResSet;
import variables.ResSet;
import builder.RequestBuilder;
import error.ResSetException;

public class CompareBuilderTest {
	@Test
	public void ComparableRequestBehavior() throws ResSetException {
		Request r;
		 
		Pair<Integer, Integer> a = new Pair<>(5, 5);
		r = new CompareRequest(new PairResSet<>(a));
		
		//assert statements
		assertEquals("Bad Response", new BooleanResSet(true), r.execute());
		
		r = new CompareRequest(null);
		r.setInput(new PairResSet<>(a));
		assertEquals("Bad Response", new BooleanResSet(true), r.execute());
	}
	
	@Test
	public void ComparableRequestBehavior2() throws ResSetException {
		
		List<Pair<Boolean,Boolean>> boolPairs = new ArrayList<>();		
		boolPairs.add(new Pair<>(true, true));
		boolPairs.add(new Pair<>(false, true));
		boolPairs.add(new Pair<>(false, false));
		
		List<Pair<String,String>> StringPairs = new ArrayList<>();		
		StringPairs.add(new Pair<>("hola", "chao"));
		StringPairs.add(new Pair<>("hola", "hola"));
		StringPairs.add(new Pair<>("432", "432"));
		
		ResSet set1 = new PairResSet<>(boolPairs);
		ResSet set2 = new PairResSet<>(StringPairs);
		
		Request r1 = new CompareRequest(set1);
		Request r2 = new CompareRequest(set2);
		
		List<Boolean> res = new ArrayList<>();
		res.add(false);
		res.add(true);
		res.add(false);
		
		//assert statements
		assertEquals("Bad Response", new BooleanResSet(res), r1.execute());
		assertEquals("Bad Response", new BooleanResSet(res), r2.execute());
	}
	
	@Test
	public void CompareRequestBuilder() throws ResSetException {
		
		String ocho = new JSONObject().put("var", 8).put("type", "int").toString();
		String dos = new JSONObject().put("var", 2).put("type", "int").toString();
		String cuatro = new JSONObject().put("var", 4).put("type", "int").toString();
		
		String dosD = new JSONObject().put("var", 2.0).put("type", "float").toString();
		String diesyseisD = new JSONObject().put("var", 16.0).put("type", "float").toString();
		
		String get = "{" +
				"\"id\":4,"+
				"\"method\":\"get\","+
				"\"x\":["+dos+","+dos+"],"+
				"\"y\":["+ocho+","+cuatro+"]"+
				"}";
		String compare = "{" +
				"\"id\":5,"+
				"\"method\":\"compare\","+
				"\"arg1\":["+dos+","+dos+"],"+
				"\"arg2\":["+dos+","+cuatro+"]"+
				"}";
		
		String compare2 = "{" +
				"\"id\":5,"+
				"\"method\":\"compare\","+
				"\"arg1\":"+get+","+
				"\"arg2\":["+diesyseisD+","+dosD+"]"+
				"}";
		
		Request r1 = new RequestBuilder().build(new JSONObject(compare));
		Request r2 = new RequestBuilder().build(new JSONObject(compare2));
		
		List<Boolean> res1 = new ArrayList<>();
		res1.add(true);
		res1.add(false);
		List<Boolean> res2 = new ArrayList<>();
		res2.add(false);
		res2.add(true);
		
		ResSet result1 = r1.execute();
		ResSet result2 = r2.execute();
		
		//assert statements
		assertEquals("Bad Response", new BooleanResSet(res1), result1);
		assertEquals("Bad Response", new BooleanResSet(res2), result2);
	}
	
	

}
