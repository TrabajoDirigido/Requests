package test;
import static org.junit.Assert.assertEquals;
import interfaces.Request;

import java.util.ArrayList;
import java.util.List;

import javafx.util.Pair;

import org.json.JSONObject;
import org.junit.Test;

import requests.GetRequest;
import variables.DoubleResSet;
import variables.PairResSet;
import variables.ResSet;
import builder.RequestBuilder;
import error.ResSetException;

public class GetBuilderTest {
	@Test
	public void GetRequestBehavior() throws ResSetException {
		Request r;
		Pair<Integer, Integer> a = new Pair<>(5, 4);
		r = new GetRequest(new PairResSet<>(a));
		
		//assert statements
		assertEquals("Bad Response", new DoubleResSet(20.0), r.execute());
		
		r = new GetRequest(null);
		r.setInput(new PairResSet<>(a));
		assertEquals("Bad Response", new DoubleResSet(20.0), r.execute());
	}
	
	@Test
	public void GetRequestBehavior2() throws ResSetException {
		Request r;
		
		List<Pair<Integer,Integer>> pairs = new ArrayList<>();
		
		pairs.add(new Pair<>(5, 4));
		pairs.add(new Pair<>(10, 3));
		pairs.add(new Pair<>(2, 5));
		
		ResSet set = new PairResSet<>(pairs);
		
		r = new GetRequest(set);
		
		List<Double> res = new ArrayList<>();
		res.add(20.0);
		res.add(30.0);
		res.add(10.0);
		
		//assert statements
		assertEquals("Bad Response", new DoubleResSet(res), r.execute());	
	}
	
	@Test
	public void GetRequestBuilder() throws ResSetException {
		
		String ocho = new JSONObject().put("var", 8).put("type", "int").toString();
		String dos = new JSONObject().put("var", 2).put("type", "int").toString();
		String cuatro = new JSONObject().put("var", 4).put("type", "int").toString();
		String get = "{" +
				"\"id\":4,"+
				"\"method\":\"get\","+
				"\"x\":["+dos+","+dos+"],"+
				"\"y\":["+ocho+","+cuatro+"]"+
				"}";
		
		Request r = new RequestBuilder().build(new JSONObject(get));
		
		List<Double> res = new ArrayList<>();
		res.add(8.0);
		res.add(16.0);
		
		ResSet result = r.execute();
		
		//assert statements
		assertEquals("Bad Response", new DoubleResSet(res), result);	
	}

}