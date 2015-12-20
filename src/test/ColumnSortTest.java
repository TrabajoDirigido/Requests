package test;
import static org.junit.Assert.assertEquals;
import interfaces.Request;

import org.json.JSONObject;
import org.junit.Test;

import variables.BooleanResSet;
import variables.ResSet;
import builder.RequestBuilder;
import error.ResSetException;

public class ColumnSortTest {
	
	@Test
	public void columnSortTest() throws ResSetException {
		
		String i = new JSONObject().put("var", "2").put("type", "int").toString();
		
		String uno = new JSONObject().put("var", "1").put("type", "int").toString();
		String dos = new JSONObject().put("var", "2").put("type", "int").toString();
		String tres = new JSONObject().put("var", "3").put("type", "int").toString();
		String cuatro = new JSONObject().put("var", "4").put("type", "int").toString();
		
		String f = new JSONObject().put("var", "false").put("type", "boolean").toString();
		
		String get = "{" +
				"\"id\":1,"+
				"\"method\":\"get\","+
				"\"x\":["+i+","+i+","+i+","+i+"],"+
				"\"y\":["+uno+","+dos+","+tres+","+cuatro+"]"+
				"}";
		
		String sort = "{" +
				"\"id\":2,"+
				"\"method\":\"sort\","+
				"\"des\":"+f+","+
				"\"vals\":"+get+","+
				"}";

		
		String compare = "{" +
				"\"id\":5,"+
				"\"method\":\"compare\","+
				"\"arg1\":"+get+","+
				"\"arg2\":"+sort+","+
				"}";
		
		String logic = "{" +
				"\"id\":8,"+
				"\"method\":\"logic\","+
				"\"type\":\"and\","+
				"\"vals\":"+compare+
				"}";
		
		Request r = new RequestBuilder().build(new JSONObject(logic));
		
		ResSet set = r.execute();
		
		assertEquals("Bad Response", new BooleanResSet(true), set);
	}	
	

}

