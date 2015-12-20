package test;
import static org.junit.Assert.assertEquals;
import interfaces.Request;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.junit.Test;

import requests.MaxRequest;
import variables.DoubleResSet;
import variables.IntegerResSet;
import variables.ResSet;
import variables.StringResSet;
import builder.RequestBuilder;
import error.ResSetException;

public class MaxBuilderTest {
	
	@Test
	public void MaxRequestBehavior() throws ResSetException {
		
		List<Integer> ints = new ArrayList<>();		
		ints.add(4);
		ints.add(15);
		ints.add(7);
		
		ResSet set;
		Request r;
		
		set = new IntegerResSet(ints);		
		r = new MaxRequest(set);
		
		assertEquals("Bad Response", new IntegerResSet(15), r.execute());
	}
	
	@Test
	public void MaxRequestBehavior2() throws ResSetException {
		
		List<String> strings = new ArrayList<>();		
		strings.add("hola");
		strings.add("adios");
		strings.add("chao");
		
		ResSet set;
		Request r;
		
		set = new StringResSet(strings);		
		r = new MaxRequest(set);
		
		assertEquals("Bad Response", new StringResSet("hola"), r.execute());
	}
	
	
	
	@Test
	public void MaxRequestBuilder() throws ResSetException {
		
		String ocho = new JSONObject().put("var", 8).put("type", "int").toString();
		String dos = new JSONObject().put("var", 2).put("type", "int").toString();
		String cuatro = new JSONObject().put("var", 4).put("type", "int").toString();
		
		
		String get = "{" +
				"\"id\":4,"+
				"\"method\":\"get\","+
				"\"x\":["+dos+","+dos+"],"+
				"\"y\":["+ocho+","+cuatro+"]"+
				"}";
		
		String max = "{" +
				"\"id\":6,"+
				"\"method\":\"max\","+
				"\"vals\":"+get+
				"}";
		
		
		Request r = new RequestBuilder().build(new JSONObject(max));
		
		assertEquals("Bad Response", new DoubleResSet(16.0), r.execute());
	}	
	

}
