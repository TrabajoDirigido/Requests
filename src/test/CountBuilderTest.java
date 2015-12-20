package test;
import static org.junit.Assert.assertEquals;
import interfaces.Request;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.junit.Test;

import requests.CountRequest;
import variables.BooleanResSet;
import variables.IntegerResSet;
import variables.ResSet;
import builder.RequestBuilder;
import error.ResSetException;

public class CountBuilderTest {
	
	@Test
	public void CountRequestBehavior() throws ResSetException {
		
		List<Boolean> bools = new ArrayList<>();		
		bools.add(true);
		bools.add(true);
		bools.add(true);
		ResSet set;
		Request r;
		
		set = new BooleanResSet(bools);		
		r = new CountRequest(set);
		
		assertEquals("Bad Response", new IntegerResSet(3), r.execute());
		
		bools.add(false);
		set = new BooleanResSet(bools);	
		r = new CountRequest(set);
		
		//assert statements
		assertEquals("Bad Response", new IntegerResSet(4), r.execute());
	}
	
	@Test
	public void LogicRequestBuilder() throws ResSetException {
		
		String ocho = new JSONObject().put("var", 8).put("type", "int").toString();
		String dos = new JSONObject().put("var", 2).put("type", "int").toString();
		String cuatro = new JSONObject().put("var", 4).put("type", "int").toString();
		
		String dosD = new JSONObject().put("var", 2.0).put("type", "double").toString();
		String diesyseisD = new JSONObject().put("var", 16.0).put("type", "double").toString();
		
		String get = "{" +
				"\"id\":4,"+
				"\"method\":\"get\","+
				"\"x\":["+dos+","+dos+"],"+
				"\"y\":["+ocho+","+cuatro+"]"+
				"}";
		
		String compare = "{" +
				"\"id\":5,"+
				"\"method\":\"compare\","+
				"\"arg1\":"+get+","+
				"\"arg2\":["+diesyseisD+","+dosD+"]"+
				"}";
		
		
		String logic = "{" +
				"\"id\":8,"+
				"\"method\":\"logic\","+
				"\"type\":\"and\","+
				"\"vals\":"+compare+
				"}";
		
		
		String count = "{" +
				"\"id\":8,"+
				"\"method\":\"count\","+
				"\"vals\":"+logic+
				"}";
		
		String count2 = "{" +
				"\"id\":8,"+
				"\"method\":\"count\","+
				"\"vals\":"+compare+
				"}";
		
		Request r = new RequestBuilder().build(new JSONObject(count));
		Request r2 = new RequestBuilder().build(new JSONObject(count2));
		

		assertEquals("Bad Response", new IntegerResSet(1), r.execute());
		assertEquals("Bad Response", new IntegerResSet(2), r2.execute());
	}	
	

}
