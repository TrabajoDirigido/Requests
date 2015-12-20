package test;
import static org.junit.Assert.assertEquals;
import interfaces.Request;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.junit.Test;

import requests.AndRequest;
import requests.OrRequest;
import variables.BooleanResSet;
import variables.ResSet;
import builder.RequestBuilder;
import error.ResSetException;

public class LogicBuilderTest {
	@Test
	public void AndRequestBehavior() throws ResSetException {
		Request r;
		r = new AndRequest(new BooleanResSet(true));
		
		//assert statements
		assertEquals("Bad Response", new BooleanResSet(true), r.execute());
		
		r = new AndRequest(null);
		r.setInput(new BooleanResSet(false));
		assertEquals("Bad Response", new BooleanResSet(false), r.execute());
	}
	
	@Test
	public void AndRequestBehavior2() throws ResSetException {
		
		List<Boolean> bools = new ArrayList<>();		
		bools.add(true);
		bools.add(true);
		bools.add(true);
		ResSet set;
		Request r;
		
		set = new BooleanResSet(bools);		
		r = new AndRequest(set);
		
		assertEquals("Bad Response", new BooleanResSet(true), r.execute());
		
		bools.add(false);
		set = new BooleanResSet(bools);	
		r = new AndRequest(set);
		
		//assert statements
		assertEquals("Bad Response", new BooleanResSet(false), r.execute());
	}
	
	@Test
	public void OrRequestBehavior() throws ResSetException {
		Request r;
		r = new OrRequest(new BooleanResSet(true));
		
		//assert statements
		assertEquals("Bad Response", new BooleanResSet(true), r.execute());
		
		r = new OrRequest(null);
		r.setInput(new BooleanResSet(false));
		assertEquals("Bad Response", new BooleanResSet(false), r.execute());
	}
	
	@Test
	public void OrRequestBehavior2() throws ResSetException {
		
		List<Boolean> bools = new ArrayList<>();		
		bools.add(false);
		bools.add(false);
		bools.add(false);
		ResSet set;
		Request r;
		
		set = new BooleanResSet(bools);		
		r = new OrRequest(set);
		
		assertEquals("Bad Response", new BooleanResSet(false), r.execute());
		
		bools.add(true);
		set = new BooleanResSet(bools);	
		r = new OrRequest(set);
		
		//assert statements
		assertEquals("Bad Response", new BooleanResSet(true), r.execute());
	}
	
	@Test
	public void LogicRequestBuilder() throws ResSetException {
		
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
				"\"arg1\":"+get+","+
				"\"arg2\":["+diesyseisD+","+dosD+"]"+
				"}";
		
		String t = new JSONObject().put("var", true).put("type", "bool").toString();
		String f = new JSONObject().put("var", false).put("type", "bool").toString();
		
		String logic1 = "{" +
				"\"id\":8,"+
				"\"method\":\"logic\","+
				"\"type\":\"or\","+
				"\"vals\":["+t+","+f+"]"+
				"}";
		
		String logic2 = "{" +
				"\"id\":8,"+
				"\"method\":\"logic\","+
				"\"type\":\"and\","+
				"\"vals\":"+compare+
				"}";
		
		Request r1 = new RequestBuilder().build(new JSONObject(logic1));
		Request r2 = new RequestBuilder().build(new JSONObject(logic2));
		
		assertEquals("Bad Response", new BooleanResSet(true), r1.execute());
		assertEquals("Bad Response", new BooleanResSet(false), r2.execute());
	}	
	

}
