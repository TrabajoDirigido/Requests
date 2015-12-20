package test;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import variables.ObjectResSet;
import variables.ResSet;
import variables.StringResSet;
import error.ResSetException;

public class ResSetTest {
	
	@Test
	public void equal() throws ResSetException {
		List<String> data = new ArrayList<>();
		data.add("hola");
		data.add("chao");
		ResSet set1 = new ObjectResSet(data);
		
		List<String> data2 = new ArrayList<>();
		data2.add("chao");
		data2.add("hola");
		ResSet set2 = new ObjectResSet(data2);
		
		assertEquals("Should be equal", set1, set2);
		
		
		data2.add("plas");
		set2 = new ObjectResSet(data2);
		
		assertThat(set1, not(equalTo(set2)));
	}
	
	@Test
	public void sort() throws ResSetException {
		List<String> data = new ArrayList<>();
		data.add("chao");
		data.add("hola");
		data.add("adios");
		StringResSet set1 = new StringResSet(data);
		
		List<String> data2 = new ArrayList<>();
		data2.add("adios");
		data2.add("chao");
		data2.add("hola");
		StringResSet set2 = new StringResSet(data2);

		assertEquals("Should be equal", set2.toString(), set1.sort(false).toString());
		assertEquals("Should be equal", set1.sort(true).toString(), set2.sort(true).toString());
	}
	
	@Test
	public void minMax() throws ResSetException {
		List<String> data = new ArrayList<>();
		data.add("hola");
		data.add("chao");
		data.add("adios");
		StringResSet set1 = new StringResSet(data);
		assertEquals("Should be equal", set1.getMin(), new StringResSet("adios"));
		assertEquals("Should be equal", set1.getMax(), new StringResSet("hola"));
	}

}
