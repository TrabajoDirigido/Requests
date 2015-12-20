package test;
import static org.junit.Assert.assertEquals;
import interfaces.Request;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.junit.Test;

import requests.MinRequest;
import variables.DoubleResSet;
import variables.IntegerResSet;
import variables.ResSet;
import variables.StringResSet;
import builder.RequestBuilder;
import error.ResSetException;

public class MinBuilderTest {
	
	@Test
	public void MinRequestBehavior() throws ResSetException {
		
		List<Integer> ints = new ArrayList<>();		
		ints.add(4);
		ints.add(15);
		ints.add(7);
		
		ResSet set;
		Request r;
		
		set = new IntegerResSet(ints);		
		r = new MinRequest(set);
		
		assertEquals("Bad Response", new IntegerResSet(4), r.execute());
	}
	
	@Test
	public void MinRequestBehavior2() throws ResSetException {
		
		List<String> strings = new ArrayList<>();		
		strings.add("hola");
		strings.add("adios");
		strings.add("chao");
		
		ResSet set;
		Request r;
		
		set = new StringResSet(strings);		
		r = new MinRequest(set);
		
		assertEquals("Bad Response", new StringResSet("adios"), r.execute());
	}
	
	
	
	@Test
	public void MinRequestBuilder() throws ResSetException {
		
		String ocho = new JSONObject().put("var", 8).put("type", "int").toString();
		String dos = new JSONObject().put("var", 2).put("type", "int").toString();
		String cuatro = new JSONObject().put("var", 4).put("type", "int").toString();
		
		
		String get = "{" +
				"\"id\":4,"+
				"\"method\":\"get\","+
				"\"x\":["+dos+","+dos+"],"+
				"\"y\":["+ocho+","+cuatro+"]"+
				"}";
		
		String min = "{" +
				"\"id\":6,"+
				"\"method\":\"min\","+
				"\"vals\":"+get+
				"}";
		
		
		Request r = new RequestBuilder().build(new JSONObject(min));
		
		assertEquals("Bad Response", new DoubleResSet(8.0), r.execute());
	}	
	

}
