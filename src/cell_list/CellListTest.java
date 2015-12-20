package cell_list;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.util.Pair;

import org.junit.Test;

import error.ResSetException;

public class CellListTest {
	
	@Test
	public void SecondIsPuntual() throws ResSetException, CellListException {
		
		ExcelCellList<Integer> x = new PuntualCellList<Integer>(2);
		ExcelCellList<Integer> y = new PuntualCellList<Integer>(5);
		List<Pair<Integer,Integer>> res = new ArrayList<>();
		res.add(new Pair<>(2,5));
		assertEquals("Bad Response", res, x.getPairs(y));
		
		Integer a[] = new Integer[]{3,4,5};
		x = new DefaultCellList<Integer>(Arrays.asList(a));
		y = new PuntualCellList<Integer>(5);
		res.clear();
		res.add(new Pair<>(3,5));
		res.add(new Pair<>(4,5));
		res.add(new Pair<>(5,5));
		assertEquals("Bad Response", res, x.getPairs(y));
		
		List<Integer> defaultList = new ArrayList<>();
		defaultList.add(4);
		defaultList.add(7);
		defaultList.add(2);
		x = new DefaultCellList<Integer>(defaultList);
		y = new PuntualCellList<Integer>(5);
		res.clear();
		res.add(new Pair<>(4,5));
		res.add(new Pair<>(7,5));
		res.add(new Pair<>(2,5));
		assertEquals("Bad Response", res, x.getPairs(y));		
	}
	
	@Test
	public void SecondIsDefault() throws ResSetException, CellListException {
		List<Integer> defaultList = new ArrayList<>();
		defaultList.add(4);
		defaultList.add(7);
		defaultList.add(2);
		
		ExcelCellList<Integer> x = new PuntualCellList<Integer>(2);
		ExcelCellList<Integer> y = new DefaultCellList<Integer>(defaultList);
		List<Pair<Integer,Integer>> res = new ArrayList<>();
		res.add(new Pair<>(2,4));
		res.add(new Pair<>(2,7));
		res.add(new Pair<>(2,2));
		assertEquals("Bad Response", res, x.getPairs(y));
		
		List<Integer> defaultList2 = new ArrayList<>();
		defaultList2.add(3);
		defaultList2.add(5);
		defaultList2.add(8);
		x = new DefaultCellList<Integer>(defaultList2);
		y = new DefaultCellList<Integer>(defaultList);
		res.clear();
		res.add(new Pair<>(3,4));
		res.add(new Pair<>(5,7));
		res.add(new Pair<>(8,2));
		assertEquals("Bad Response", res, x.getPairs(y));		
	}
	
	@Test(expected=CellListException.class)
	public void DefaultsWithDifferentSizesTest() throws CellListException {
		List<Integer> defaultList = new ArrayList<>();
		defaultList.add(4);
		defaultList.add(7);
		defaultList.add(2);
		
		List<Integer> defaultList2 = new ArrayList<>();
		defaultList2.add(3);
		defaultList2.add(5);
		defaultList2.add(8);
		defaultList2.add(9);
		ExcelCellList<Integer> x = new DefaultCellList<Integer>(defaultList2);
		ExcelCellList<Integer> y = new DefaultCellList<Integer>(defaultList);
		
		x.getPairs(y);
	}
}

