package test;
import static org.junit.Assert.assertEquals;
import interfaces.Request;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.junit.Test;

import requests.SortRequest;
import variables.BooleanResSet;
import variables.DoubleResSet;
import variables.ResSet;
import variables.StringResSet;
import builder.RequestBuilder;
import error.ResSetException;

public class SortBuilderTest {
	@Test
	public void SortRequestBehavior() throws ResSetException {
		Request r;
		StringResSet set;
		 
		List<String> stringList = new ArrayList<>();
		stringList.add("hola");
		stringList.add("chao");
		stringList.add("mundo");
		
		set = new StringResSet(stringList);		
		r = new SortRequest(set,true);
		
		//assert statements
		assertEquals("Bad Response", set.sort(true).toString(), r.execute().toString());
		
		r = new SortRequest(set,false);
		assertEquals("Bad Response", set.sort(false).toString(), r.execute().toString());
	}
	
	@Test
	public void SortRequestBehavior2() throws ResSetException {		
		Request r;
		BooleanResSet set;
		 
		List<Boolean> booleanList = new ArrayList<>();
		booleanList.add(true);
		booleanList.add(true);
		booleanList.add(false);
		
		set = new BooleanResSet(booleanList);		
		r = new SortRequest(set,true);
		
		//assert statements
		assertEquals("Bad Response", set.sort(true).toString(), r.execute().toString());
		
		r = new SortRequest(set,false);
		assertEquals("Bad Response", set.sort(false).toString(), r.execute().toString());
	}
	
	@Test
	public void SortRequestBuilder() throws ResSetException {
		
		String ocho = new JSONObject().put("var", 8).put("type", "int").toString();
		String dos = new JSONObject().put("var", 2).put("type", "int").toString();
		String cuatro = new JSONObject().put("var", 4).put("type", "int").toString();
		String uno = new JSONObject().put("var", 1).put("type", "int").toString();
		
		String t = new JSONObject().put("var", true).put("type", "boolean").toString();
		String f = new JSONObject().put("var", false).put("type", "boolean").toString();
		
		String get = "{" +
				"\"id\":4,"+
				"\"method\":\"get\","+
				"\"x\":["+dos+","+cuatro+","+uno+","+ocho+"],"+
				"\"y\":["+dos+","+dos+","+dos+","+cuatro+"]"+
				"}";
		
		String sort = "{" +
				"\"id\":5,"+
				"\"method\":\"sort\","+
				"\"des\":"+f+","+
				"\"vals\":"+get+","+
				"}";
		
		String sort2 = "{" +
				"\"id\":5,"+
				"\"method\":\"sort\","+
				"\"des\":"+t+","+
				"\"vals\":"+get+","+
				"}";
		
		Request r1 = new RequestBuilder().build(new JSONObject(sort));
		Request r2 = new RequestBuilder().build(new JSONObject(sort2));
		
		//System.out.println(r2.request());
		
		List<Double> res = new ArrayList<>();
		res.add(4.0);
		res.add(8.0);
		res.add(2.0);
		res.add(32.0);
		
		DoubleResSet set = new DoubleResSet(res);
		
		ResSet result1 = r1.execute();
		ResSet result2 = r2.execute();
		
		//assert statements
		assertEquals("Bad Response", set.sort(false).toString(), result1.toString());
		assertEquals("Bad Response", set.sort(true).toString(), result2.toString());
	}

}
